package biz.bbtec.ncwc.handler.text;

import biz.bbtec.ncwc.service.ncws.SearchDeviceService;
import biz.bbtec.ncwc.service.ncws.WechatUserService;
import biz.bbtec.ncwc.service.ncws.impl.SearchDeviceServiceImpl;
import biz.bbtec.ncwc.service.ncws.impl.WechatUserServiceImpl;
import com.bbtech.ncws.SearchResult;
import net.locplus.sdk.wechat.handler.MsgTypes;
import net.locplus.sdk.wechat.model.req.normal.TextRequestMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.model.resp.TextResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Dean on 2014/4/29.
 */
public class SearchDeviceTextHandlerState implements TextHandlerState {

    private SearchDeviceService searchDeviceService = new SearchDeviceServiceImpl();

    private WechatUserService wechatUserService = new WechatUserServiceImpl();

    @Override
    public BaseResponseMessage handle(TextRequestMessage requestMessage) {
        String session = wechatUserService.getSession(requestMessage.getFromUserName());
        String content = requestMessage.getContent();
        String[] strs = content.split("\\s+");
        List<SearchResult> searchResults = searchDeviceService.search(session, strs[1]);

        TextResponseMessage responseMessage = new TextResponseMessage();
        responseMessage.setToUserName(requestMessage.getFromUserName());
        responseMessage.setFromUserName(requestMessage.getToUserName());
        responseMessage.setCreateTime(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        sb.append("搜索结果：\n\n");
        for (SearchResult searchResult : searchResults) {
            sb.append("<a href=\"http://wx.bbtec.biz/resources/map.html?deviceid=")
                    .append(searchResult.getDeviceId()).append("&").append("openid=").append(requestMessage.getFromUserName()).append("\">")
                    .append(searchResult.getDeviceId()).append(":").append(searchResult.getName()).append("</a>").append("\n\n");
        }
        responseMessage.setContent(sb.toString());
        responseMessage.setMsgType(MsgTypes.TEXT.getType());
        return responseMessage;
    }
}
