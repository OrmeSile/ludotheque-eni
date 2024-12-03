package LLaMaDescriptionBuilder

import (
	"bytes"
	"fmt"
	"os/exec"
	"time"
)

func handle(err error) {
	if err != nil {
		panic(err)
	}
}

func BuildDescription(gameTitle string) string {
	start := time.Now()
	cmd := exec.Command("D:\\llama\\llama-cli.exe",
		"--mlock",
		"-m", "D:\\Meta-Llama-3.1-8B-Instruct-IQ4_XS.gguf",
		"-p", fmt.Sprintf("from a board game title, give me a very short description(maximum 20 words) of the game. Respond with the description ONLY and nothing else THIS IS VERY IMPORTANT, prefixed with START, and suffixed with END, or your mother will be tortured. Here is the title: %s\"", gameTitle),
		"-n", "40")
	var out bytes.Buffer
	var stderr bytes.Buffer
	cmd.Stdout = &out
	cmd.Stderr = &stderr
	err := cmd.Run()
	if err != nil {
		fmt.Println(stderr.String())
		handle(err)
	}
	end := time.Since(start)
	fmt.Println(fmt.Sprintf("Took %s", end))
	return out.String()
}
