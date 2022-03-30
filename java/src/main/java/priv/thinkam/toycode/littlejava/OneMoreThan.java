package priv.thinkam.toycode.littlejava;

/**
 * @author yanganyu
 * @date 2022/3/17
 */
class OneMoreThan extends Num {
    private Num num;

    public OneMoreThan(Num num) {
        this.num = num;
    }

    @Override
    int getValue() {
        return num.getValue() + 1;
    }
}
