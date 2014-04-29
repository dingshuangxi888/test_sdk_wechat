package biz.bbtec.ncwc.service.ncws;

import com.bbtech.ncws.UserLogin;

/**
 * Created by Administrator on 2014/4/29.
 */
public interface LoginService {
    public UserLogin login(String username, String passwd);
}
