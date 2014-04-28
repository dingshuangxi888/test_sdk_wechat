package biz.bbtec.ncwc.controller;

import biz.bbtec.ncwc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014/4/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "bind", method = RequestMethod.POST)
    public
    @ResponseBody
    String bind(User user) {
        System.out.println(user.getUserName() + ":" + user.getPassword());
        return "SUCCESS";
    }
}
