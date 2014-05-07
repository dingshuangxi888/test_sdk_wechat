package biz.bbtec.ncwc.controller;

import net.locplus.sdk.wechat.servlet.WeChatExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2014/5/6.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Resource(name = "weChatExecutor")
    private WeChatExecutor weChatExecutor;

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        try {
            weChatExecutor.doGet(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Using time for this request is {}", (System.currentTimeMillis() - start));
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        try {
            weChatExecutor.doPost(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Using time for this request is {}", (System.currentTimeMillis() - start));
    }
}
