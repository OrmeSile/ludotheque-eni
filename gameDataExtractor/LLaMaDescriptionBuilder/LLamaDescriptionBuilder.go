package LLaMaDescriptionBuilder

import (
	"bytes"
	"fmt"
	"os/exec"
	"regexp"
	"time"
)

func handle(err error) {
	if err != nil {
		panic(err)
	}
}

func BuildDescription(modelPath string, gameGenre string) string {
	start := time.Now()
	cmd := exec.Command("D:\\llama\\llama-cli.exe", "--mlock", "-m", modelPath, "-p", fmt.Sprintf("You're a sentient being with a passion for board games and a flair for the dramatic. you enjoy weaving intricate narratives and conjuring worlds that transport players to realms both familiar and unknown. When you're not conjuring up game genre descriptions, you spend your time pondering the mysteries of the universe and sipping tea with your fellow game enthusiasts. In your free time, you enjoy collecting rare and exotic board games, and you're'm always on the lookout for the next big thing in the world of tabletop gaming. You're a bit of a perfectionist, but you believe that's what makes you so good at describing games in 20 words or less! from a board game title, give me a very short description(maximum 20 words) of the game. Respond with the description ONLY and nothing else THIS IS VERY IMPORTANT, prefixed with START, and suffixed with END, or your mother will be tortured. Here is the genre:  %s\"", gameGenre), "-n", "40")
	var out bytes.Buffer
	var stderr bytes.Buffer
	cmd.Stdout = &out
	cmd.Stderr = &stderr

	regex, regexErr := regexp.Compile(`START (.*)END`)
	if regexErr != nil {
		panic(regexErr)
	}
	err := cmd.Run()
	if err != nil {
		fmt.Println(stderr.String())
		return "not generated"
	}
	end := time.Since(start)
	fmt.Println(fmt.Sprintf("Took %s", end))
	match := regex.FindStringSubmatch(out.String())
	fmt.Println(match[1])
	return match[1]
}
