package com.mt.controller;

import com.mt.bean.User;
import com.mt.service.UserService;
import com.mt.spring.XmlBeanDemo;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired XmlBeanDemo demo;

    @Autowired
    private        UserService userService;
    private static Logger      log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("register")
    public String register(final HttpServletResponse r) {
        r.setStatus(401);
        userService.registerComplete();
        return "register ok";
    }

    @RequestMapping("getUser/{userId}")
    public User findUser(
        @PathVariable(name = "userId", required = true) Long userId,
        HttpServletRequest request,
        HttpServletResponse r
    ) {
        long x1   = System.currentTimeMillis();
        User user = null;
        try {
            user = userService.fetchUser(userId);
        } catch (Exception e) {
            log.error("something went wrong, error:{}", e.getMessage(), e);
        } finally {
            log.info("[INPUT_OUTPUT_USER]: [URI]{}, userId: {}, [RESPONSE]{}[TIME]{}ms[END]", request.getRequestURI(), userId, user, System.currentTimeMillis() - x1);
        }
        return user;
    }

    @RequestMapping("findUser/{name}")
    public Collection<User> findUser(
        @PathVariable(name = "name", required = true) String name,
        HttpServletRequest request,
        HttpServletResponse r
    ) {
        long             x1   = System.currentTimeMillis();
        Collection<User> user = null;
        try {
            user = userService.fetchUserByName(name);
            System.out.println(user == null);
        } catch (Exception e) {
            log.error("something went wrong, error:{}", e.getMessage(), e);
        } finally {
            log.info("[INPUT_OUTPUT_USER]: [URI]{}, userId: {}, [RESPONSE]{}[TIME]{}ms[END]", request.getRequestURI(), 1, user, System.currentTimeMillis() - x1);
        }
        return user;
    }

    @CacheEvict
    @RequestMapping("lock")
    public User lockUser(
        String name,
        Integer site,
        HttpServletRequest request
    ) {
        long x1   = System.currentTimeMillis();
        User user = null;
        try {
            user = userService.lockUnique(site, name);
        } catch (Exception e) {
            log.error("something went wrong, error:{}", e.getMessage(), e);
        }
        return user;
    }

    @RequestMapping("update")
    public User updateUser(
        String name,
        Integer site,
        HttpServletRequest request
    ) {
        long x1   = System.currentTimeMillis();
        User user = null;
        try {
            user = userService.updateUser(site, name);
        } catch (Exception e) {
            log.error("something went wrong, error:{}", e.getMessage(), e);
        }
        return user;
    }

    /**
     * test MVCC(Multiple Version Concurrent Control)
     * in a isolation level = RR
     * read a user several times and should get same result in a same transaction
     * @param userId
     * @return
     */
    @RequestMapping("/user/get")
    public User snapshotRead(Long userId) {
        return userService.getUser(userId);
    }

    /**
     * different with snapshot read, here we get different result in a same transaction
     * first read use snapshot read
     * second read use current read(select for update)
     * but, you should clear hibernate session before select, because there are cache in the session
     * @param userId
     * @return
     */
    @RequestMapping("/user/current")
    public User currentRead(Long userId) {
        return userService.getCurrentUser(userId);
    }


    /**
     * use to update a record by userId
     * @param user
     * @return
     */
    @RequestMapping("/user/upd")
    public User upd(User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/user/autoSave")
    public User hibernateSession(Long userId) {
        return userService.hopeUserFieldsChange(userId);
    }
}
