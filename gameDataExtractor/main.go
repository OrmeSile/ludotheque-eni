package main

import (
	"fmt"
	gameDataExtractor "gameDataExtractor/CsvExtractor"
	"time"
)

func main() {
	start := time.Now()
	gameDataExtractor.ExtractData("C:\\code\\ludotheque\\gameDataExtractor\\CsvExtractor\\data\\boardgames_ranks.csv")
	fmt.Println(fmt.Sprintf("%v elapsed", time.Since(start)))
}
