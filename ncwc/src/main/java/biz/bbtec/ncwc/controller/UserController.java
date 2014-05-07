package biz.bbtec.ncwc.controller;

import biz.bbtec.ncwc.model.WechatUser;
import biz.bbtec.ncwc.service.ncws.LoginService;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import com.bbtech.ncws.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dean on 2014/4/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private WechatUserService wechatUserService;

    @RequestMapping(value = "bind", method = RequestMethod.POST)
    @ResponseBody
    public String bind(WechatUser user) {
        UserLogin userLogin = loginService.login(user.getUserName(), user.getPassword());
        if (userLogin != null && userLogin.getSession() != null) {
            boolean bindSuccess = wechatUserService.bind(user.getOpenid(), userLogin.getSession());
            if (bindSuccess) {
                return "SUCCESS";
            }
        }
        return "FAILURE";
    }
}
