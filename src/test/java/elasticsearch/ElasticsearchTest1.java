package elasticsearch;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ElasticsearchTest1
 * Create by cy on 2018/5/5
 **/
public class ElasticsearchTest1 {
    private Logger logger = LoggerFactory.getLogger(ElasticsearchTest1.class);

    public final static String HOST = "127.0.0.1";

    public final static int PORT = 9300;//http请求的端口是9200，客户端是9300

    /**
     * 测试Elasticsearch客户端连接
     * @Title: test1
     * @author sunt
     * @date 2017年11月22日
     * @return void
     * @throws UnknownHostException
     */
    @SuppressWarnings("resource")
    @Test
    public void test1() throws UnknownHostException {
        //创建客户端
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));

        System.out.println("Elasticsearch connect info:" + client.toString());

        //关闭客户端
        client.close();
    }


}
