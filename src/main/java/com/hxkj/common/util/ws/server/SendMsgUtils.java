package com.hxkj.common.util.ws.server;

import com.hxkj.common.util.ExecutorServiceUtils;
import com.hxkj.common.util.StringUtils;
import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

/**
 * 发送 websocket 消息
 * @author chuang
 * @date 2018/9/10
 */
public class SendMsgUtils {

    private   final  static Logger LOG = Logger.getLogger(SendMsgUtils.class);

    /**
     * 发送消息给部分用户
     * @param sessionIds
     * @param msg
     */
    public final static void sendToUsers(List<String> sessionIds, String msg){
        Session session;
        for(String sessionId:sessionIds){
            sessionId = StringUtils.decryptAesHex(sessionId);
            if(sessionId == null){
                continue;
            }
            session = OnlineUserContainer.SESSIONID_SESSION.get(sessionId);
            if(session!=null){
                try {
                    session.getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    WebSocketServer.closeSession(session);
                    LOG.error(e.getMessage(),e);
                }
            }
        }
    }


    /**
     * 广播通知所有在线客户
     * @param message   消息文本
     * @param sessionId 排除的当前session
     */
    public final static  void broadcast(String message,String sessionId) {
        if(LOG.isInfoEnabled()){
            LOG.info("broadcast msg : "+message);
        }
        ExecutorServiceUtils.pool.submit(new Runnable() {
            @Override
            public void run() {
                Session session;
                for (String mapKey : OnlineUserContainer.SESSIONID_SESSION.keySet()) {
                    // 排除自己
                    if(sessionId == null || mapKey.equals(sessionId)){
                        continue;
                    }
                    session =  OnlineUserContainer.SESSIONID_SESSION.get(mapKey);
                    try {
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        WebSocketServer.closeSession(session);
                        LOG.error(e.getMessage(),e);
                    }
                }
            }
        });

    }
}
