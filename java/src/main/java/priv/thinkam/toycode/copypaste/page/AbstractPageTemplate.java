package priv.thinkam.toycode.copypaste.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 分页查询模板（Java8之前的写法）
 *
 * @author yanganyu
 * @date 2019/7/28
 */
public abstract class AbstractPageTemplate<E> {
    /**
     * "pageNumber"
     */
    public static final String PAGE_NUMBER = "pageNumber";
    /**
     * "pageSize"
     */
    public static final String PAGE_SIZE = "pageBegin";
    /**
     * "pageBegin"
     */
    private static final String PAGE_BEGIN = "pageBegin";

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
     * @param paramMap 分页查询参数（key需要包含”pageNumber“和”pageSize“，否则默认查询第一页的20条记录）
     * @return 分页结果对象集合
     */
    public Page<E> page(Map<String, Object> paramMap) {
        Objects.requireNonNull(paramMap);
        // 获取页数
        Integer pageNumber = (Integer) paramMap.get(PAGE_NUMBER);
        // 校验页数，不合法则设置默认值
        pageNumber = pageNumber == null || pageNumber <= 0 ? 1 : pageNumber;
        // 获取页大小，不合法设置默认值
        Integer pageSize = (Integer) paramMap.computeIfAbsent(PAGE_SIZE, k -> 20);
        // 计算SQL中limit的offset（Mysql）
        paramMap.put(PAGE_BEGIN, (pageNumber - 1) * pageSize);
        // 查询符合条件的总记录数
        long total = pageCount(paramMap);
        if (total <= 0) {
            return new Page<>(0L, new ArrayList<>());
        } else {
            // 查询符合条件的一页记录
            return new Page<>(total, pageList(paramMap));
        }
    }

    /**
     * 查询符合条件的总记录数
     *
     * @param paramMap 分页查询参数（key需要包含”pageNumber“和”pageSize“，否则默认查询第一页的20条记录）
     * @return 总记录数
     */
    abstract long pageCount(Map<String, Object> paramMap);

    /**
     * 查询符合条件的所有记录
     *
     * @param paramMap 分页查询参数（key需要包含”pageNumber“和”pageSize“，否则默认查询第一页的20条记录）
     * @return 分页结果集合
     */
    abstract List<E> pageList(Map<String, Object> paramMap);
}
