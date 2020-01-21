package priv.thinkam.toycode.util;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * list utility
 *
 * @author yanganyu
 * @date 2020/1/21 13:52
 */
public class ListUtil {

    /**
     * Splitting a list into N parts of approximately equal length
     *
     * @param list the list to return consecutive sublists of
     * @param splitCount the count you want to split
     * @param <T> list's type
     * @see <a href="https://stackoverflow.com/a/2135920">Splitting a list into N parts of approximately equal length</a>
     * @return a list of consecutive sublists
     */
    public static <T> List<List<T>> split(List<T> list, int splitCount) {
        Objects.requireNonNull(list);
        Preconditions.checkArgument(splitCount > 0 && splitCount <= list.size());
        int quotient = list.size() / splitCount;
        int remainder = list.size() % splitCount;
        List<List<T>> listList = new ArrayList<>();
        for (int i = 0; i < splitCount; i++) {
            listList.add(list.subList(i * quotient + Math.min(i, remainder),
                    (i + 1) * quotient + Math.min(i + 1, remainder)));
        }
        return listList;
    }

}
