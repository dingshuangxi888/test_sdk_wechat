package net.locplus.sdk.wechat.model.req.normal;

/**
 * 地理位置消息
 * Created by Administrator on 2014/4/18.
 */
public class LocationRequestMessage extends BaseNormalRequestMessage {
    /**
     * 地理位置维度
     */
    private float Location_X;
    /**
     * 地理位置经度
     */
    private float Location_Y;
    /**
     * 地图缩放大小
     */
    private int Scale;
    /**
     * 地理位置信息
     */
    private String Label;

    public float getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(float location_X) {
        Location_X = location_X;
    }

    public float getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(float location_Y) {
        Location_Y = location_Y;
    }

    public int getScale() {
        return Scale;
    }

    public void setScale(int scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
