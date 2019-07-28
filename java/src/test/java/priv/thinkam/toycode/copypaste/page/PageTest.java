package priv.thinkam.toycode.copypaste.page;

import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author thinkam
 * @date 2019/7/28 17:17
 */
public class PageTest {
    private UserService userService = new UserService();

    @Test
    public void abstractPageTemplateDemo() {
        Page<User> userPage = userService.page(ImmutableMap.of(
                PageUtil.PAGE_NUMBER, 1,
                PageUtil.PAGE_SIZE, 20
                // 其他参数...
        ));
    }

    @Test
    public void pageUtilDemo() {
        Page<User> userPage = PageUtil.page(userService::pageCount, userService::pageList, ImmutableMap.of(
                PageUtil.PAGE_NUMBER, 1,
                PageUtil.PAGE_SIZE, 20
                // 其他参数...
        ));
    }

    @Data
    private static class User {
    }

    private static class UserService extends AbstractPageTemplate<User> {
        private UserDAO userDAO = new UserDAO();

        @Override
        public long pageCount(Map<String, Object> paramMap) {
            return userDAO.pageCount(paramMap);
        }

        @Override
        public List<User> pageList(Map<String, Object> paramMap) {
            return userDAO.pageList(paramMap);
        }
    }

    private static class UserDAO  {
        public long pageCount(Map<String, Object> paramMap) {
            // select count(*) from user where ...
            return 0;
        }

        public List<User> pageList(Map<String, Object> paramMap) {
            // select * from user where ... limit pageBegin, pageSize
            return new ArrayList<>();
        }
    }
}
