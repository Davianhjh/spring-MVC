package com.airline.controller;

import com.airline.entity.TestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by qxb-810 on 2018/7/10.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String testGET () {
        return "WELCOME";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String testJSONArray (@RequestBody List<TestEntity> body) {
        if (body == null || body.size() == 0)
            return "PARAM ERROR";
        else {
            for (TestEntity testEntity: body) {
                if (!isInteger(testEntity.getId()))
                    return "PARAM ERROR";
            }
        }
        return "SUCCESS";
    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
