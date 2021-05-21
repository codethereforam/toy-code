package priv.thinkam.toycode.concurrent.primecount;

/**
 * common constant and function
 *
 * @author yanganyu
 * @date 2021/5/18
 */
class Common {
    /**
     * 要计算的数字范围上限
     */
    static final int NUMBER_UPPER_LIMIT = 100000;

    static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * if num is prime return 1, otherwise return 0
     */
    static int mapToPrimeCount(int num) {
        return isPrime(num) ? 1 : 0;
    }
}
