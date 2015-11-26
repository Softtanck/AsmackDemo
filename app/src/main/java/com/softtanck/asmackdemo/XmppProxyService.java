package com.softtanck.asmackdemo;

import android.util.Log;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * Created by winterfell on 11/24/2015.
 */
public class XmppProxyService {


    private XMPPConnection connection;

    /**
     * 连接服务器
     *
     * @return
     */
    public boolean conServer() {
        try {
            //SASLError using DIGEST-MD5: not-authorized
            SASLAuthentication.supportSASLMechanism("PLAIN", 0);
            ConnectionConfiguration config = new ConnectionConfiguration("10.50.40.95", 5222);
            /** 是否启用调试 */
            config.setDebuggerEnabled(true);
            /** 设置安全模式*/
            connection = new XMPPTCPConnection(config);
            /** 是否启用安全验证 */
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            /** 建立连接 */
            connection.connect();
            connection.login("tanck", "422013");
//            connection.disconnect();
            Log.d("Tanck", "Connection Success");

            ChatManager manager = ChatManager.getInstanceFor(connection);
            manager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean b) {
                    chat.addMessageListener(new MessageListener() {
                        @Override
                        public void processMessage(Chat chat, Message message) {
                            Log.d("Tanck", "Revice Msg:"+message.getBody()+"form:"+message.getFrom());
                            Message msg = new Message("q");
                            msg.setBody("test");
                            try {
                                chat.sendMessage(msg);
                            } catch (SmackException.NotConnectedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
