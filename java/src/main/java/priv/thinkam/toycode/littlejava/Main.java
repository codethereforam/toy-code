package priv.thinkam.toycode.littlejava;

/**
 * @author yanganyu
 * @date 2022/3/17
 */
class Main {

    public static void main(String[] args) {
        Zero zero = new Zero();
        System.out.println(zero.getValue());
        System.out.println(new OneMoreThan(new OneMoreThan(new OneMoreThan(zero))).getValue());
    }

}
