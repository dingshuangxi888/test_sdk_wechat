package biz.bbtec.ncwc.model;

/**
 * Created by Administrator on 2014/4/28.
 */
public class WechatUser {
    private String userName;
    private String password;
    private String openid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
