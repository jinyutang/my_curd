package com.hxkj.common.util.ws.server;

import com.hxkj.auth.model.AuthUser;
import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * websocket server
 */
@ServerEndpoint("/ws-server")
public class WebSocketServer {

    private final static Logger LOG = Logger.getLogger(WebSocketServer.class);
    // 无操作最大超时时间  1800 秒
    private final static Integer MAX_IDLE_TIMEOUT=1800 * 1000;

    /**
     * 建立 ws 连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        session.setMaxIdleTimeout(MAX_IDLE_TIMEOUT);
        // 输出默认信息
        if(LOG.isInfoEnabled()){
            LOG.info("--------------- basicRemote:   " + session.getBasicRemote());
            LOG.info("--------------- maxIdleTimeout: " + session.getMaxIdleTimeout());
        }
        // url参数
        String queryString = session.getQueryString();
        String userId = queryString.split("=")[1];
        // aes 解密
        try{
            userId = UserIdEncryptUtils.decrypt(userId,UserIdEncryptUtils.CURRENT_USER_ID_AESKEY);
        }catch (Exception e){
            closeSession(session);
            LOG.error(e.getMessage(),e);
            return;
        }
        AuthUser authUser = AuthUser.dao.findById(userId);
        if(authUser==null){
            closeSession(session);
            return;
        }

        SendMsgUtils.broadcast(authUser.getName() + " online now ...",session.getId());
        OnlineUserContainer.addOnlineUser(authUser,session);
    }


    /**
     * 关闭 ws 连接
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        AuthUser authUser = OnlineUserContainer.SESSIONID_USER.get(sessionId);
        OnlineUserContainer.removeOnlineUser(authUser,session);
        SendMsgUtils.broadcast(authUser.getName() + " offline now ...",sessionId);
    }

    /**
     * 处理文本消息
     * https://docs.oracle.com/javaee/7/api/javax/websocket/OnMessage.html
     * @param msg json 格式的数据
     * @param session
     */
    @OnMessage // 一个 endpoint 只能有一个该方法注解
    public void onMessage(String msg, Session session) {
        AuthUser authUser = OnlineUserContainer.SESSIONID_USER.get(session.getId());
        SendMsgUtils.broadcast(authUser.getName()+": "+msg,session.getId());
    }


    /**
     * 其它方法发生异常 会触发此方法
     *
     * @param session
     * @param t
     */
    @OnError
    public void onError(Session session, Throwable t) {
        LOG.error(t.getMessage(),t);
        closeSession(session);
        String sessionId = session.getId();
        AuthUser authUser  = OnlineUserContainer.SESSIONID_USER.get(sessionId);
        OnlineUserContainer.removeOnlineUser(authUser,session);
        SendMsgUtils.broadcast(authUser.getName() + " offline  [exception: "+t.getMessage()+"]",sessionId);
    }


    /**
     * 关闭当前会话
     * @param session
     */
    public static   void closeSession(Session session){
        try {
            session.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
        }
    }
}