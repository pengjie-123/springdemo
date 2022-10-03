package com.mt.service;

import com.mt.bean.Order;
import com.mt.bean.User;
import com.mt.dao.UserDao;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public interface UserService {

    public void registerComplete();
    public User create(User user);

    public User fetchUser(Long userId) throws Exception;

    public Collection<User> fetchUserByName(String name);

    public User lockUnique(Integer siteId, String name);

    public User updateUser(Integer siteId, String name);

    public User updateUser(User user);

    public User getUser(Long userId);

    public User getCurrentUser(Long userId);
    public User hopeUserFieldsChange(Long userId);
}

@Component
class UserServiceImpl implements UserService {

    @Autowired ApplicationContext context;
    @Autowired UserDao userDao;

    @Override public void registerComplete() {
        System.out.println(Thread.currentThread().getName()+"---register success");
        //need send email
        //need send skype
        // use spring publish, here we use observer design pattern
        User user = new User();
        user.setUserId(1L);
        user.setUserName("Tomcat");
        context.publishEvent(user);
    }

    /**
     * User with collections orders. userId in Order entity is null at first userDao.save(),
     * after that we set it into Order after we get the saved User. then userId will be assigned to
     * Order after the transaction was committed.
     *
     * @param user
     * @return
     */
    @Override public User create(User user) {
        User saved = userDao.save(user);
        for (Order order : saved.getOrders()) {
            order.setUserId(saved.getUserId());
        }
        return saved;
    }

    @Override public User fetchUser(Long userId) throws Exception {
        User user;
        try {
            Assert.notNull(userId, "userId is required");
            user = userDao.getUser(userId);
            user.setUserName("test123456");

            userDao.updateUser(user);
            doSomething();

        } catch (Exception e) {
                e.printStackTrace();
                throw e;
        }
        return user;
    }

    @Override public Collection<User>  fetchUserByName(String name) {
        return userDao.getUseBynNamer(name);
    }

    @Override public User lockUnique(Integer siteId, String name) {
        System.out.println(Thread.currentThread().getName() + "-----start a transaction for getUnique");
        User u =  userDao.getUserForUpdate(siteId, name);
        return u;
    }

    @Override public User updateUser(Integer siteId, String name) {
        System.out.println(Thread.currentThread().getName() + "-----start a transaction for selecteUpdate");

        User user = userDao.getUserForUpdateNoLock(siteId, name);
        user.setEmail("00000000.com");
        return userDao.updateUser(user);

    }

    @Override public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override public User getUser(Long userId) {
//        User before = userDao.getUser(userId);
//        System.out.println(before);
//        try {
//            Thread.sleep(30000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        User after = userDao.getUser(userId);
        System.out.println("lazy fetch test");
        System.out.println(after);
        Collection orders = after.getOrders();
        System.out.println(after);
        return after;
    }

    @Override public User getCurrentUser(Long userId) {
        User before = userDao.getUser(userId);
        System.out.println(before);
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User after = userDao.getCurrentUser(userId);
        System.out.println(after);
        return after;
    }

    //Hibernate:
    //    select
    //        user0_.PK_USER_ID as PK_USER_1_0_0_,
    //        user0_.EMAIL as EMAIL2_0_0_,
    //        user0_.NICK_NAME as NICK_NAM3_0_0_,
    //        user0_.PHONE_NUMBER as PHONE_NU4_0_0_,
    //        user0_.SITE_ID as SITE_ID5_0_0_,
    //        user0_.USER_NAME as USER_NAM6_0_0_
    //    from
    //        USER user0_
    //    where
    //        user0_.PK_USER_ID=?
    //Hibernate:
    //    UPDATE
    //        USER
    //    SET
    //        USER_NAME=?
    //    WHERE
    //        PK_USER_ID=?
    //Hibernate:
    //    update
    //        USER
    //    set
    //        EMAIL=?,
    //        NICK_NAME=?,
    //        PHONE_NUMBER=?,
    //        SITE_ID=?,
    //        USER_NAME=?
    //    where
    //        PK_USER_ID=?

    //hibernate will trigger extra one more update sql in case if original object's fields was modified and you have a native update sql before
    //this is a 'bug' of hibernate session, there are 2 solutions
    //1. dont update any fields of the original object which was fetched from db.
    //2. add session.clear() in userDao.updateUserByFields(u)
    @Override public User hopeUserFieldsChange(Long userId) {
        User u = userDao.getUser(userId);
        u.setUserName("hibernatetest222");
        userDao.updateUserByFields(u);
        return u;
    }

    private void doSomething() throws Exception {
        throw new Exception();
    }
}



