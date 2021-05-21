package main

import "fmt"
import "time"

const _limit = 100000

func isPrime(num int) bool {
	for i := 2; i < num; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}

func mapToPrimeCount(num int) int {
	if isPrime(num) {
		return 1
	} else {
		return 0
	}
}

func main() {
	start := time.Now()

	var sum = 0
	for i := 2; i <= _limit; i++ {
		sum += mapToPrimeCount(i)
	}
	fmt.Println(sum)

	fmt.Println(time.Now().Sub(start))
}
