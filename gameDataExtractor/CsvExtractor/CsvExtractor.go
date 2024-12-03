package CsvExtractor

import (
	"encoding/csv"
	"fmt"
	"gameDataExtractor/LLaMaDescriptionBuilder"
	"os"
	"strings"
)

func handle(err error) {
	if err != nil {
		panic(err)
	}
}

//type BGGGame struct {
//	id                  int32
//	name                string
//	yearpublished       int32
//	rank                int32
//	bayesaverage        float32
//	average             float32
//	usersrated          float32
//	is_expansion        bool
//	abstracts_ranks     int32
//	cgs_rank            int32
//	childrensgames_rank int32
//	familygames_rank    int32
//	partygames_rank     int32
//	strategygames_rank  int32
//	thematic_rank       int32
//	wargames_rank       int32
//}

func ExtractData(path string) {

	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer func(file *os.File) {
		err := file.Close()
		handle(err)
	}(file)

	reader := csv.NewReader(file)
	records, err := reader.ReadAll()
	handle(err)

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
		handle(err)
	}(writeFile)

	for i, v := range records {
		if i == 0 {
			continue
		}
		handle(err)
		result := formatToSqlString(v[1], v[2])
		//description := LLaMaDescriptionBuilder.BuildDescription(v[1])
		//modelPath := CHEMIN ABSOLU DU MODELE(C:\....)
		//result := formatToSqlStringWithDescription(modelPath, v[1], v[2])
		_, err = writeFile.Write([]byte(result))
		if i%1000 == 0 {
			_, err = writeFile.Write([]byte("COMMIT;\nBEGIN;\n"))
			handle(err)
		}
		handle(err)
	}
	_, err2 := writeFile.Write([]byte("COMMIT;\nCLOSE;"))
	handle(err2)
}

func formatToSqlString(name string, yearPublished string) string {
	return fmt.Sprintf("INSERT INTO public.game (is_active,is_rented, max_rent_days, time_of_creation, id, renter_id, description, name, year_published) values (false, false, 0, now(), gen_random_uuid(), null, null, '%s', %s);\n", sanitize(clean(name)), sanitize(clean(yearPublished)))
}

func formatToSqlStringWithDescription(description string, name string, yearPublished string) string {
	return fmt.Sprintf("INSERT INTO public.game (is_active,is_rented, max_rent_days, time_of_creation, id, renter_id, description, name, year_published) values (false, false, 0, now(), gen_random_uuid(), null, %s, '%s', %s);\n", sanitize(clean(description)), sanitize(clean(name)), sanitize(clean(yearPublished)))
}

func clean(data string) string {
	firstStep := strings.Replace(data, "'", "''", -1)
	secondStep := strings.Replace(firstStep, ";", "", -1)
	return secondStep
}

func sanitize(data string) string {
	return strings.Replace(data, ",", "", -1)
}
