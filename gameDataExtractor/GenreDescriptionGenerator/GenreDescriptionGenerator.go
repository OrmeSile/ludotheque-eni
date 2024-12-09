package GenreDescriptionGenerator

import (
	"bufio"
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

type genreInformation struct {
	genre       string
	description string
}

func newGenreInformation(genre string, description string) *genreInformation {
	return &genreInformation{genre, description}
}

func GenerateGenreDescriptionScript() {
	modelPath := "D:/Meta-Llama-3.1-8B-Instruct-IQ4_XS.gguf"
	path := "GenreDescriptionGenerator/game-genres.txt"
	file, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	defer func(file *os.File) {
		err := file.Close()
		handle(err)
	}(file)

	genres := make([]string, 0, 10)
	reader := bufio.NewScanner(file)
	for reader.Scan() {
		genres = append(genres, reader.Text())
	}
	genreDescriptions := make([]*genreInformation, 0, len(genres))
	for _, v := range genres {
		generatedDescription := LLaMaDescriptionBuilder.BuildDescription(modelPath, v)
		genreDescriptions = append(genreDescriptions, newGenreInformation(v, generatedDescription))
	}
	generateGenreInformationSqlScript(genreDescriptions)
}

func generateGenreInformationSqlScript(genreInformations []*genreInformation) {
	writeFile, createErr := os.Create("./generated-genres.sql")
	handle(createErr)
	_, writeErr := writeFile.WriteString("BEGIN;\n")
	handle(writeErr)
	for _, v := range genreInformations {
		sqlString := fmt.Sprintf("INSERT INTO public.genre (id, name, description) values (gen_random_uuid(), %s, %s)\n", v.genre, sanitize(clean(v.description)))
		_, lineErr := writeFile.WriteString(sqlString)
		if lineErr != nil {
			fmt.Println("err")
		}
	}
	_, endErr := writeFile.WriteString("COMMIT;\nCLOSE;\n")
	handle(endErr)
}

func clean(data string) string {
	firstStep := strings.Replace(data, "'", "''", -1)
	secondStep := strings.Replace(firstStep, ";", "", -1)
	return secondStep
}

func sanitize(data string) string {
	return strings.Replace(data, ",", "", -1)
}
