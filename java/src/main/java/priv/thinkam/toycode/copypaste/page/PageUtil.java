package priv.thinkam.toycode.copypaste.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToLongFunction;

/**
 * 分页查询工具类
 *
 * @author yanganyu
 * @date 2019/7/28
 */
public class PageUtil {
    /**
     * "pageNumber"
     */
    public static final String PAGE_NUMBER = "pageNumber";
    /**
     * "pageSize"
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * "pageBegin"
     */
    private static final String PAGE_BEGIN = "pageBegin";

    private PageUtil() {
    }

    /**
     * 获取分页结果对象
     * <p>
     * 示例:
     *
     * <pre>
     * {@code
     *      Page<FooDTO> fooDTOPage = PageUtil.page(mapper::selectPageCount1, mapper::selectPageEntities1, paramMap)
     * }
     * </pre>
     *
     * @param pageCountFunction     查询分页总数的方法（参数类型：{@code Map<String, Object>}；返回值类型：{@code int}）
     * @param pageQueryListFunction 查询分页记录的方法（参数类型：{@code Map<String, Object>};；返回值类型：{@code List<E>}）
     * @param paramMap              分页查询参数（key需要包含”pageNumber“和”pageSize“，否则默认查询第一页的20条记录）
     * @return 分页结果对象集合
     */
    public static <E> Page<E> page(ToLongFunction<Map<String, Object>> pageCountFunction,
                                   Function<Map<String, Object>, List<E>> pageQueryListFunction,
                                   Map<String, Object> paramMap) {
        Objects.requireNonNull(pageCountFunction);
        Objects.requireNonNull(pageQueryListFunction);
        Objects.requireNonNull(paramMap);
        Integer pageNumber = (Integer) paramMap.get(PAGE_NUMBER);
        pageNumber = pageNumber == null || pageNumber <= 0 ? 1 : pageNumber;
        Integer pageSize = (Integer) paramMap.computeIfAbsent(PAGE_SIZE, k -> 20);
        paramMap.put(PAGE_BEGIN, (pageNumber - 1) * pageSize);
        long total = pageCountFunction.applyAsLong(paramMap);
        if (total <= 0) {
            return new Page<>(0L, new ArrayList<>());
        } else {
            return new Page<>(total, pageQueryListFunction.apply(paramMap));
        }
    }
}
