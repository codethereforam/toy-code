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

func mapToPrimeCount(num int, ch chan int) {
	if isPrime(num) {
		ch <- 1
	} else {
		ch <- 0
	}
}

func main() {
	start := time.Now()

	ch := make(chan int, 1024)
	for i := 2; i <= _limit; i++ {
		go mapToPrimeCount(i, ch)
	}

	var sum = 0
	for i := 2; i <= _limit; i++ {
		sum += <-ch
	}
	fmt.Println(sum)

	fmt.Println(time.Now().Sub(start))
}
