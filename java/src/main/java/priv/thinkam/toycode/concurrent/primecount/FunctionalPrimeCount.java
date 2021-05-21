package priv.thinkam.toycode.concurrent.primecount;

import java.util.stream.IntStream;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;

/**
 * 函数式并发
 *
 * @author yanganyu
 */
class FunctionalPrimeCount {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        System.out.println(IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT)
                // comment the next line is serial version, otherwise is concurrent version
                .parallel()
                .map(Common::mapToPrimeCount)
                .sum());

        System.out.println(System.currentTimeMillis() - startTime);
    }
}