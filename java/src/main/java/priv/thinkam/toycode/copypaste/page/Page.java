package priv.thinkam.toycode.copypaste.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果对象
 *
 * @author yanganyu
 * @date 2019/7/28
 * @param <T> 分页查询对象类型
 */
@AllArgsConstructor
@Getter
@ToString
public final class Page<T> implements Serializable {
    /**
     * 总记录数
     */
    @NonNull
    private final Long total;
    /**
     * 当前记录集合
     */
    @NonNull
    private final List<T> rows;
}
