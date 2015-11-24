package com.softtanck.asmackdemo;

import android.util.Log;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
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
            Log.d("Tanck", "连接成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
