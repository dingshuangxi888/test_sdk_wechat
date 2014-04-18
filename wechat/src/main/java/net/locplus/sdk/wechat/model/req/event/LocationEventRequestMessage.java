package net.locplus.sdk.wechat.model.req.event;

/**
 * 上报地理位置事件
 * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
 * Created by Administrator on 2014/4/18.
 */
public class LocationEventRequestMessage {
    private float Latitude;
    private float Longitude;
    private float Precision;

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getPrecision() {
        return Precision;
    }

    public void setPrecision(float precision) {
        Precision = precision;
    }
}
