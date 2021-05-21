package priv.thinkam.toycode.concurrent.primecount;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.mapToPrimeCount;

/**
 * 串行版本
 *
 * @author yanganyu
 * @date 2021/5/18
 */
class SerialPrimeCount {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int sum = 0;
        for (int i = 2; i <= NUMBER_UPPER_LIMIT; i++) {
            sum += mapToPrimeCount(i);
        }
        System.out.println(sum);

        System.out.println(System.currentTimeMillis() - startTime);
    }

}
