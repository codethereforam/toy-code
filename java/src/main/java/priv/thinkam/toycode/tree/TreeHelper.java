package priv.thinkam.toycode.tree;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树工具类
 *
 * @author yanganyu
 * @date 2022/5/23
 */
public class TreeHelper {

    public static <E, VO extends BaseTreeVO<VO>> List<VO> build(List<E> elementList,
                                                                Function<E, Long> getIdFunction,
                                                                Function<E, Long> getPidFunction,
                                                                Function<E, VO> convertFunction) {
        if (CollectionUtils.isEmpty(elementList)) {
            return Collections.emptyList();
        } else {
            Map<Long, List<E>> parentIdPageMap = elementList.stream().collect(Collectors.groupingBy(getPidFunction));
            return buildTree(0L, parentIdPageMap, getIdFunction, convertFunction);
        }
    }

    private static <E, VO extends BaseTreeVO<VO>> List<VO> buildTree(Long pid,
                                                                     Map<Long, List<E>> pidElementMap,
                                                                     Function<E, Long> getIdFunction,
                                                                     Function<E, VO> convertFunction) {
        List<E> elements = pidElementMap.get(pid);
        if (CollectionUtils.isEmpty(elements)) {
            return Collections.emptyList();
        } else {
            List<VO> voList = new ArrayList<>();
            for (E element : elements) {
                VO resultDTO = convertFunction.apply(element);
                resultDTO.setChildren(buildTree(getIdFunction.apply(element), pidElementMap, getIdFunction, convertFunction));
                voList.add(resultDTO);
            }
            return voList;
        }
    }

    public static <VO extends BaseTreeVO<VO>> void print(List<VO> treeVOList) {
        print(0, treeVOList);
    }

    private static <VO extends BaseTreeVO<VO>> void print(int level, List<VO> treeVOList) {
        for (VO vo : treeVOList) {
            System.out.println(StringUtils.repeat("——", level) + vo);
            print(level + 1, vo.getChildren());
        }
    }
}
