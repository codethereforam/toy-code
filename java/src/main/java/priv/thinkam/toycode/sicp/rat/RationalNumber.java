package priv.thinkam.toycode.sicp.rat;

/**
 * 有理数
 *
 * @author thinkam
 * @date 2019/11/12 23:21
 */
public class RationalNumber {
    /**
     * 分子
     */
    private int numer;
    /**
     * 分母
     */
    private int denom;

    public RationalNumber(int numer, int denom) {
        int gcd = $.gcd(numer, denom);
        this.numer = numer / gcd;
        this.denom = denom / gcd;
    }

    /**
     * 获取分子
     *
     * @return 分子
     */
    public int getNumer() {
        return numer;
    }

    /**
     * 获取分母
     *
     * @return 分母
     */
    public int getDenom() {
        return denom;
    }

    /**
     * 加上一个有理式
     *
     * @param another 另一个有理数
     * @return 加后的值
     */
    public RationalNumber plus(RationalNumber another) {
        return new RationalNumber(this.getNumer() * another.getDenom() + another.getNumer() * this.getDenom(),
                this.getDenom() * another.getDenom());
    }

    /**
     * 乘以一个有理数
     *
     * @param another 另一个有理数
     * @return 乘后的值
     */
    public RationalNumber multiply(RationalNumber another) {
        return new RationalNumber(this.getNumer() * another.getNumer(), this.getDenom() * another.getDenom());
    }

    @Override
    public String toString() {
        return this.getNumer() + "/" + this.getDenom();
    }

    public static void main(String[] args) {
        RationalNumber a = new RationalNumber(2, 3);
        RationalNumber b = new RationalNumber(1, 12);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a + b = " + a.plus(b));
        System.out.println("a * b = " + a.multiply(b));
    }
}
