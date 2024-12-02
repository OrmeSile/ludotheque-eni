package CsvExtractor

import (
	"encoding/csv"
	"fmt"
	"os"
	"strings"
)

type BGGGame struct {
	id                  int32
	name                string
	yearpublished       int32
	rank                int32
	bayesaverage        float32
	average             float32
	usersrated          float32
	is_expansion        bool
	abstracts_ranks     int32
	cgs_rank            int32
	childrensgames_rank int32
	familygames_rank    int32
	partygames_rank     int32
	strategygames_rank  int32
	thematic_rank       int32
	wargames_rank       int32
}

func ExtractData(path string) {

	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			panic(err)
		}
	}(file)

	reader := csv.NewReader(file)
	records, err := reader.ReadAll()
	if err != nil {
		panic(err)
	}

	writeFile, err := os.Create("./games.sql")
	if err != nil {
		panic(err)
	}
	_, err = writeFile.Write([]byte("BEGIN;\n"))
	if err != nil {
		panic(err)
	}
	defer func(writeFile *os.File) {
		err := writeFile.Close()
		if err != nil {
		}
	}(writeFile)

	for i, v := range records {
		if i == 0 {
			continue
		}
		if i == 1001 {
			break
		}
		_, err = writeFile.Write([]byte(formatToSqlString(v[1], v[2])))
		if i%1000 == 0 {
			_, err = writeFile.Write([]byte("COMMIT;\nCLOSE;\n\nBEGIN;\n"))
			if err != nil {
				panic(err)
			}
		}
		if err != nil {
			panic(err)
		}
	}
	_, err2 := writeFile.Write([]byte("COMMIT\nCLOSE;"))
	if err2 != nil {
		panic(err)
	}
}

func formatToSqlString(name string, yearPublished string) string {
	return fmt.Sprintf("INSERT INTO public.game (is_active,is_rented, max_rent_days, time_of_creation, id, renter_id, description, name, year_published) values (false, false, 0, now(), gen_random_uuid(), null, null, '%s', %s);\n", strings.Replace(name, "'", "''", -1), yearPublished)
}
