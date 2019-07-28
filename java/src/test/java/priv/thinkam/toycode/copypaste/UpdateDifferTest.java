package priv.thinkam.toycode.copypaste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * UpdateDiffer test
 *
 * @author yanganyu
 * @date 2019/7/27
 */
public class UpdateDifferTest {
    private UserDAO userDAO = new UserDAO();

    @Test
    public void stupidWayDemo() {
        // MQ监听方法接收到上游系统推送过来的一条记录，只对接id、name、age字段。id是唯一键（实际中一般不会是id）
        User toUpdate = new User(1, "uu1", 20, null);
        System.out.println("toUpdate user: " + toUpdate);
        // 根据唯一键键去数据库查询查询查询
        User original = userDAO.getById(toUpdate.getId());
        System.out.println("original user: " + original);
        // 如果查到则用上游系统推送的记录去更新这条记录，如果没查到则插入一条新记录
        if (original != null) {
            // 对比两个对象，获取用于更新的对象
            User updateUser = getUserUpdate(toUpdate, original);
            // 如果两对象要比较的字段都一样就不操作，否则更新不同的字段
            if (updateUser != null) {
                // 设置主键id
                updateUser.setId(toUpdate.getId());
                System.out.println("update user: " + updateUser);
                // 根据主键id去更新
                userDAO.updateById(updateUser);
                System.out.println("updated user: " + userDAO.getById(toUpdate.getId()));
            }
        } else {
            // 插入一条新记录
        }
    }

    /**
     * 对比两个对象，获取用于更新的对象
     *
     * @param toUpdate 要更新的对象
     * @param original 原来的对象
     * @return 用于更新的对象。如果要比较的字段都一样则返回null
     */
    private User getUserUpdate(User toUpdate, User original) {
        User updateUser = new User();
        if (!original.getName().equals(toUpdate.getName())) {
            updateUser.setName(toUpdate.getName());
        }
        if (!original.getAge().equals(toUpdate.getAge())) {
            updateUser.setAge(toUpdate.getAge());
        }
        if (Stream.of(updateUser.getName(), updateUser.getAge())
                .allMatch(Objects::isNull)) {
            // 没有更新
            return null;
        }
        return updateUser;
    }

    @Test
    public void updateDifferDemo() {
        User toUpdate = new User(1, "uu1", 20, null);
        System.out.println("toUpdate user: " + toUpdate);
        User original = userDAO.getById(toUpdate.getId());
        System.out.println("original user: " + original);
        if (original != null) {
            User updateUser = new UpdateDiffer<>(original, toUpdate, User::new)
                    .diffing(User::getName, User::setName)
                    .diffing(User::getAge, User::setAge)
                    .diff();
            if (updateUser != null) {
                updateUser.setId(toUpdate.getId());
                System.out.println("update user: " + updateUser);
                userDAO.updateById(updateUser);
                System.out.println("updated user: " + userDAO.getById(toUpdate.getId()));
            }
        } else {
            // insert user
        }
    }

    @Test
    public void testUpdateDiffer1() {
        User toUpdate = new User(1, "uu1", 20, null);
        User original = userDAO.getById(toUpdate.getId());
        Assert.assertNotNull(original);
        User updateUser = new UpdateDiffer<>(original, toUpdate, User::new)
                .diffing(User::getName, User::setName)
                .diffing(User::getAge, User::setAge)
                .diff();
        Assert.assertNotNull(updateUser);
        updateUser.setId(toUpdate.getId());
        userDAO.updateById(updateUser);
        Assert.assertEquals(new User(1, "uu1", 20, 1), userDAO.getById(toUpdate.getId()));
    }

    @Test
    public void testUpdateDiffer2() {
        User toUpdate = new User(1, "u1", 18, null);
        User original = userDAO.getById(toUpdate.getId());
        Assert.assertNotNull(original);
        User updateUser = new UpdateDiffer<>(original, toUpdate, User::new)
                .diffing(User::getName, User::setName)
                .diffing(User::getAge, User::setAge)
                .diff();
        Assert.assertNull(updateUser);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private Integer id;
        private String name;
        private Integer age;
        private Integer sex;
    }

    private static class UserDAO {
        private User oneUser = new User(1, "u1", 18, 1);

        User getById(Integer id) {
            if (id != 1) {
                return null;
            }
            return oneUser;
        }

        void updateById(User user) {
            Integer id = user.getId();
            if (id == null || id != 1) {
                return;
            }
            if (user.getName() != null) {
                oneUser.setName(user.getName());
            }
            if (user.getAge() != null) {
                oneUser.setAge(user.getAge());
            }
            if (user.getSex() != null) {
                oneUser.setSex(user.getSex());
            }
        }
    }
}