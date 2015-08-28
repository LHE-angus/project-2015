package com.ebiz.mmt.r3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sap.mw.jco.JCO;

/**
 * SAP连接池管理
 * 
 * @author zhou
 */
public final class ConnectPoolManager {

    private static final String POOL_NAME = "QD_SAP_CONNECT_POOL";

    /**
     * 连接池允许进来多少个客户端连接数
     */
    private static final int MAX_CONNECTIONS = 500;

    /**
     * 连接池的固定大小
     */
    private static final int MAX_POOL_SZ = 500;

    private static JCO.Pool buildSAPPool() throws IOException {
        JCO.Pool pool = JCO.getClientPoolManager().getPool(POOL_NAME);
        // 如果连接池不存在,则创建之
        if (pool == null) {
            Properties props = new Properties();
            InputStream is = ConnectPoolManager.class.getResourceAsStream("connect_url.properties");
            try {
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
            JCO.addClientPool(POOL_NAME, MAX_CONNECTIONS, props);
            pool = JCO.getClientPoolManager().getPool(POOL_NAME);
            pool.setMaxPoolSize(MAX_POOL_SZ);
            pool.setMaxConnections(MAX_CONNECTIONS);

        }
        return pool;
    }

    public static JCO.Client getConnectionInSAPPool() throws IOException {
        JCO.Client mConnection = null;
        JCO.Pool pool = JCO.getClientPoolManager().getPool(POOL_NAME);
        if (pool == null) {
            pool = buildSAPPool();
        }
        mConnection = pool.getPoolManager().getClient(POOL_NAME);
        return mConnection;
    }


    public static void releaseClient(JCO.Client client) {
        JCO.releaseClient(client);
    }

}
