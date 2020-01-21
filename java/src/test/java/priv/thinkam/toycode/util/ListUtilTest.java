package priv.thinkam.toycode.util;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yanganyu
 * @date 2020/1/21 17:02
 */
class ListUtilTest {

    @Test
    void split() {
        System.out.println(ListUtil.split(rangeList(1, 11), 3));
        System.out.println(ListUtil.split(rangeList(1, 12), 3));
        System.out.println(ListUtil.split(rangeList(1, 13), 3));
        System.out.println(ListUtil.split(rangeList(1, 14), 3));
        System.out.println(ListUtil.split(rangeList(0, 10), 9));
        System.out.println(ListUtil.split(rangeList(0, 8), 6));
    }

    public static List<Integer> rangeList(int start, int end) {
        return IntStream.range(start, end).boxed().collect(Collectors.toList());
    }
}