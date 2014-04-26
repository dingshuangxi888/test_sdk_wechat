package net.locplus.sdk.wechat.servlet;

import net.locplus.sdk.wechat.config.WeChatConfiguration;
import net.locplus.sdk.wechat.handler.MessageProcessingHandler;
import net.locplus.sdk.wechat.model.req.AllRequestMessage;
import net.locplus.sdk.wechat.model.req.SignatureMessage;
import net.locplus.sdk.wechat.model.resp.BaseResponseMessage;
import net.locplus.sdk.wechat.service.WeChatService;
import net.locplus.sdk.wechat.util.WeChatUtil;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatExecutor {

    private final static Logger logger = LoggerFactory.getLogger(WeChatExecutor.class);

    public static void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;

        logger.info(request.getQueryString());

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        logger.info("Message from wechat to verify the authenticity [signature={}, timestamp={}, nonce={}, echostr={}]", signature, timestamp, nonce, echostr);

        SignatureMessage signatureMessage = new SignatureMessage(signature, timestamp, nonce, echostr);

        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token = WeChatConfiguration.TOKEN;
        result = doGet(signatureMessage, _token);
        response.getWriter().write(result);
    }

    public static void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        String xmlMsg = WeChatUtil.inputStream2String(in);
        String result = doPost(xmlMsg);
        response.getWriter().write(result);
    }


    public static String doGet(SignatureMessage signatureMessage, String token) {
        String result = "error";
        if (WeChatService.checkSignature(signatureMessage, token)) {
            result = signatureMessage.getEchostr();
        }
        return result;
    }

    public static String doPost(String xmlMessage) {
        logger.info("Message from wechat user {}", xmlMessage);
        String result = null;

        AllRequestMessage requestMessage = WeChatUtil.parseRequestMessage(xmlMessage);

        StringBuilder messageObject = new StringBuilder("net.locplus.sdk.wechat.model.req.");
        StringBuilder handlerMethod = new StringBuilder("on");

        String msgType = requestMessage.getMsgType();
        String upperMsgType = WeChatUtil.upperFirst(msgType);
        if ("event".equalsIgnoreCase(msgType)) {
            String event = requestMessage.getEvent();
            String upperEvent = WeChatUtil.upperFirst(event);
            messageObject.append(upperEvent);
            handlerMethod.append(upperEvent);
        } else {
            messageObject.append("normal.");
        }
        messageObject.append(upperMsgType).append("RequestMessage");
        handlerMethod.append(upperMsgType).append("MessageReceived");

        try {
            Class targetClazz = Class.forName(messageObject.toString());
            BeanCopier copier = BeanCopier.create(AllRequestMessage.class, targetClazz, false);
            Object targetObject = targetClazz.newInstance();
            copier.copy(requestMessage, targetObject, null);

            Class<MessageProcessingHandler> handlerClazz = (Class<MessageProcessingHandler>) Class.forName(WeChatConfiguration.MESSAGE_PROCESSING_HANDLER);
            MessageProcessingHandler messageProcessingHandler = handlerClazz.newInstance();

            Method handleMethod = messageProcessingHandler.getClass().getMethod(handlerMethod.toString(), targetClazz);
            if (handleMethod != null) {
                handleMethod.invoke(messageProcessingHandler, targetObject);
            }

            BaseResponseMessage responseMessage = messageProcessingHandler.getResponseMessage();
            result = WeChatUtil.parseResponseMessage(responseMessage);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.info("Message response to wechat user {}", result);
        return result;
    }
}
