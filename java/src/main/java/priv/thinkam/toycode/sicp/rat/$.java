package priv.thinkam.toycode.sicp.rat;

/**
 * @author thinkam
 * @date 2019/11/28 22:32
 */
public class $ {
    /**
     * 求最大公约数
     *
     * @param a a
     * @param b b
     * @return 最大公约数
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, Math.floorMod(a, b));
        }
    }

    public void a() {
        b();
    }

    public void b() {
        a();
    }

    public static void main(String[] args) {
        new $().a();
    }
}
