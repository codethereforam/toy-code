package priv.thinkam.toycode.algorithm;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TopK
 *
 * @author yanganyu
 * @date 2022/3/4
 */
class TopK {

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(1, 10000001).boxed().collect(Collectors.toList());
        Collections.shuffle(list);

        long t1 = System.currentTimeMillis();
//        System.out.println(topKByHeap(list, 5));
        System.out.println(topKBySort(list, 5));
        System.out.println(System.currentTimeMillis() - t1);
    }

    private static List<Integer> topKByHeap(List<Integer> list, int topK) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(topK);
        int i = 0;
        for (; i < topK; i++) {
            heap.add(list.get(i));
        }
        for (; i < list.size(); i++) {
            Integer current = list.get(i);
            if (current > heap.element()) {
                heap.poll();
                heap.add(current);
            } else {
                // do nothing
            }
        }
        return new ArrayList<>(heap);
    }

    private static List<Integer> topKBySort(List<Integer> list, int topK) {
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .limit(topK)
                .collect(Collectors.toList());
    }


}
