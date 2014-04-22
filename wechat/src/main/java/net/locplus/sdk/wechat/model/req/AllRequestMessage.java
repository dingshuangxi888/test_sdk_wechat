package net.locplus.sdk.wechat.model.req;

/**
 * Created by Administrator on 2014/4/22.
 */
public class AllRequestMessage {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;

    //normal
    private long MsgId;

    //Text
    private String Content;

    //Media
    private String MediaId;

    //Image
    private String PicUrl;

    //Voice
    private String Format;

    //Video
    private String ThumbMediaId;

    //Location
    private float Location_X;
    private float Location_Y;
    private int Scale;
    private String Label;

    //Link
    private String Title;
    private String Description;
    private String Url;


    //Event
    private String Event;

    //Scan Event or Menu Event
    private String EventKey;
    private String Ticket;

    //Location Event
    private float Latitude;
    private float Longitude;
    private float Precision;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

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
