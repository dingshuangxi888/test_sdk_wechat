package biz.bbtec.ncwc.service.ncws;

/**
 * Created by Administrator on 2014/4/29.
 */
public interface WechatUserService {

    public boolean bind(String openid, String session);

    public boolean unbind(String openid);

    public String getSession(String openid);
}
