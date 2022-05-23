package priv.thinkam.toycode.tree;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;

/**
 * @author yanganyu
 * @date 2022/5/23
 */
public class TreeHelperTest extends TestCase {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class MyTree extends BaseTreeVO<MyTree> {
        private Long id;
        private Long pid;
        private String name;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class MyTreeVO extends BaseTreeVO<MyTreeVO> {
        private Long id;
        private Long pid;
        private String name;
    }

    @Test
    public void testBuild() {
        List<MyTree> treeElements = Lists.newArrayList(
                new MyTree(1L, 0L, "e1"),
                new MyTree(2L, 0L, "e2"),
                new MyTree(3L, 1L, "e3"),
                new MyTree(4L, 1L, "e4"),
                new MyTree(5L, 3L, "e5")
        );

        List<MyTreeVO> treeVo = TreeHelper.build(treeElements, MyTree::getId, MyTree::getPid,
                e -> new MyTreeVO(e.getId(), e.getPid(), e.getName()));
        TreeHelper.print(treeVo);
        System.out.println("");

        List<MyTree> tree = TreeHelper.build(treeElements, MyTree::getId, MyTree::getPid, e -> e);
        TreeHelper.print(tree);
    }
}