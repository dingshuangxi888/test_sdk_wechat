package net.locplus.sdk.wechat.servlet;

import net.locplus.sdk.wechat.handler.DefaultMessageProcessingHandler;
import net.locplus.sdk.wechat.handler.MessageProcessingHandler;
import net.locplus.sdk.wechat.model.req.AllRequestMessage;
import net.locplus.sdk.wechat.service.WeChatService;
import net.locplus.sdk.wechat.model.req.SignatureMessage;
import net.locplus.sdk.wechat.util.WeChatUtil;
import net.sf.cglib.beans.BeanCopier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Administrator on 2014/4/22.
 */
public class WeChatExecutor {

    public static void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        SignatureMessage signatureMessage = new SignatureMessage(signature, timestamp, nonce, echostr);

        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token;
        if (pathInfo != null && !pathInfo.isEmpty()) {
            _token = pathInfo.substring(1);
            result = doGet(signatureMessage, _token);
        }
        response.getWriter().write(result);
    }

    public static void doPost(HttpServletRequest request, HttpServletResponse response) {

    }


    public static String doGet(SignatureMessage signatureMessage, String token) {
        String result = "error";
        if (WeChatService.checkSignature(signatureMessage, token)) {
            result = signatureMessage.getEchostr();
        }
        return result;
    }

    public static String doPost(String message) {
        AllRequestMessage requestMessage = WeChatUtil.parseRequestMessage(message);

        StringBuilder messageObject = new StringBuilder("net.locplus.sdk.wechat.model.req");
        StringBuilder handlerMethod = new StringBuilder("on");

        String msgType = requestMessage.getMsgType();
        String upperMsgType = WeChatUtil.upperFirst(msgType);
        if ("event".equalsIgnoreCase(msgType)) {
            String event = requestMessage.getEvent();
            String upperEvent = WeChatUtil.upperFirst(event);
            messageObject.append(upperEvent);
            handlerMethod.append(upperEvent);
        }
        messageObject.append(upperMsgType).append("RequestMessage");
        handlerMethod.append(upperMsgType).append("MessageReceived");

        try {
            Class targetClazz = Class.forName(messageObject.toString());
            BeanCopier copier = BeanCopier.create(AllRequestMessage.class, targetClazz, false);
            Object targetObject = targetClazz.newInstance();
            copier.copy(requestMessage, targetObject, null);

            MessageProcessingHandler messageProcessingHandler = new DefaultMessageProcessingHandler();
            Method handleMethod = messageProcessingHandler.getClass().getMethod(handlerMethod.toString(), targetClazz);
            if (handleMethod != null) {
                handleMethod.invoke(messageProcessingHandler, targetObject);
            }

            //TODO: handle OutMessage

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

        return null;
    }
}
