package priv.thinkam.toycode.tree;

import lombok.Data;

import java.util.List;

/**
 * base tree
 *
 * @author yanganyu
 * @date 2022/5/23
 */
@Data
public abstract class BaseTreeVO<T> {
    /**
     * children
     */
    private List<T> children;
}