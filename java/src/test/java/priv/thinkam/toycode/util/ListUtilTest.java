package priv.thinkam.toycode.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author yanganyu
 * @date 2020/1/21 17:02
 */
@Slf4j
class ListUtilTest {

    @Test
    void split() {
        checkSplit(1, 10, 3);
        checkSplit(1, 11, 3);
        checkSplit(1, 12, 3);
        checkSplit(1, 13, 3);
        checkSplit(1, 11, 6);
        checkSplit(1, 12, 6);
        checkSplit(1, 13, 6);
        checkSplit(1, 14, 6);
    }

    public static <T> void checkSplit(int start, int end, int splitCount) {
        List<Integer> originalList = rangeList(start, end);
        List<List<Integer>> splitLists = ListUtil.split(originalList, splitCount);
        log.info("split lists[{}, {}] to [{}] part: {}", start, end, splitCount, splitLists);
        // 校验每个集合里面元素加起来和原集合一样
        assertEquals(originalList, splitLists.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        // 校验拆分的集合数正确
        assertEquals(splitCount, splitLists.size());
        // 校验集合拆分均匀
        int minPartSize = (end - start + 1) / splitCount;
        for (List<Integer> list : splitLists) {
            assertTrue(list.size() == minPartSize || list.size() == minPartSize + 1);
        }
    }

    public static List<Integer> rangeList(int start, int end) {
        return IntStream.range(start, end + 1).boxed().collect(Collectors.toList());
    }
}