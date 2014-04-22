package net.locplus.sdk.wechat.servlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String method = req.getMethod();
        if ("get".equalsIgnoreCase(method)) {
            this.doGet(req, resp);
        } else {
            this.doPost(req, resp);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        WeChatExecutor.doGet(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        WeChatExecutor.doPost(request, response);
    }

    @Override
    public void destroy() {

    }
}
