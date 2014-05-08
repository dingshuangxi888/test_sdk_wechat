package biz.bbtec.ncwc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dean on 2014/5/8.
 */
public class ShortUrlServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(ShortUrlServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("http://www.baidu.com");
    }
}
