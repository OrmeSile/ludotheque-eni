package main

import (
	"fmt"
	"gameDataExtractor/CsvExtractor"
	"time"
)

type fn func()

func main() {
	extractDataStart := time.Now()
	CsvExtractor.ExtractData("D:\\code\\ludotheque\\gameDataExtractor\\CsvExtractor\\data\\boardgames_ranks.csv")
	fmt.Println(fmt.Sprintf("%v elapsed", time.Since(extractDataStart)))
	//timeFuncExecution(GenreDescriptionGenerator.GenerateGenreDescriptionScript)
}

func timeFuncExecution(delegate fn) {
	start := time.Now()
	delegate()
	fmt.Println(fmt.Sprintf("%v elapsed", time.Since(start)))
}
