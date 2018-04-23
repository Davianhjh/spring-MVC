package com.airline.controller;

import com.airline.test.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/test")
public class testController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello, my first SpringMVC restful API!";
    }

    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    @ResponseBody
    public User postUser(@RequestBody User user) {
        String id = user.getId();
        String name = user.getName();
        ApplicationContext context = new ClassPathXmlApplicationContext("../applicationContext.xml");
        User res = (User) context.getBean("User");
        res.setId(id);
        res.setName(name);
        return res;
    }

    @RequestMapping(value = "/postHeader", method = RequestMethod.POST)
    @ResponseBody
    public String postHeader(@RequestHeader HttpHeaders headers, @CookieValue("Agiview") String cookie) {
        String token = headers.getFirst("token");
        String res = "The token is " + token + ". And cookie is Agiview:" + cookie;
        return res;
    }
}
