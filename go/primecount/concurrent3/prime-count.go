package main

import (
	"fmt"
	"time"
)

const _limit = 100000
const _nProcess = 12

func isPrime(num int) bool {
	for i := 2; i < num; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}

func splitPrimeCount(in chan int) chan int {
	out := make(chan int)
	go func() {
		var sum = 0
		for n := range in {
			if isPrime(n) {
				sum++
			}
		}
		out <- sum
		close(out)
	}()
	return out
}

func createNumberChanel() chan int {
	out := make(chan int)
	go func() {
		for i := 2; i <= _limit; i++ {
			out <- i
		}
		close(out)
	}()
	return out
}

func main() {
	start := time.Now()

	numberChanel := createNumberChanel()
	channels := make([]chan int, _nProcess)
	for i := 0; i < _nProcess; i++ {
		channels[i] = splitPrimeCount(numberChanel)
	}

	var sum = 0
	for _, ch := range channels {
		sum += <-ch
	}
	fmt.Println(sum)

	fmt.Println(time.Now().Sub(start))
}
