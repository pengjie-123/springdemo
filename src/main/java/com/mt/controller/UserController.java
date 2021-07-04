package com.mt.controller;

import com.mt.bean.User;
import com.mt.service.UserService;
import com.mt.spring.XmlBeanDemo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired XmlBeanDemo demo;

    @Autowired
    private UserService userService;
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("register")
    public String register(final HttpServletResponse r) {
        r.setStatus(401);
        userService.registerComplete();
        return "register ok";
    }

    @RequestMapping("getUser/{userId}")
    public User findUser(@PathVariable(name = "userId", required = true) Long userId,
                         HttpServletRequest request, HttpServletResponse r) {
        long x1 = System.currentTimeMillis();
        User user = null;
        try {
            user = userService.fetchUser(userId);
        } catch (Exception e) {
            log.error("something went wrong, error:{}", e.getMessage(), e);
        } finally {
            log.info("[INPUT_OUTPUT_USER]: [URI]{}, userId: {}, [RESPONSE]{}[TIME]{}ms[END]", request.getRequestURI(), userId, user, System.currentTimeMillis()-x1);
        }
        return user;
    }
}
