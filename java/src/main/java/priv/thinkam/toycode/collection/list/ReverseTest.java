package priv.thinkam.toycode.collection.list;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试翻转集合
 *
 * <p>
 * reference article:
 * <a href="http://www.techiedelight.com/reverse-arraylist-java/">Reverse an ArrayList in Java in 5 different ways</a>
 * </p>
 *
 * @author thinkam
 * @date 2018/04/18
 */
public class ReverseTest {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> reversedList1 = Lists.reverse(list1);
        System.out.println(list1.toString());
        System.out.println(reversedList1);
        list1.set(0, 0);
        System.out.println(list1);
        System.out.println(reversedList1);

        System.out.println("--------------");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<Integer> reversedList2 = new ArrayList<>(list2);
        Collections.reverse(reversedList2);
        System.out.println(list2);
        System.out.println(reversedList2);
        list2.set(0, 0);
        System.out.println(list2);
        System.out.println(reversedList2);

        System.out.println("------------------");
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        List<Integer> reversedList3 = IntStream.range(0, list3.size())
                .map(i -> (list3.size() - 1 - i))
                .mapToObj(list3::get)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list3);
        System.out.println(reversedList3);
    }
}
