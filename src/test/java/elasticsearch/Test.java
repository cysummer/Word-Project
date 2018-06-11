package elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

/**
 * Test
 * Create by cy on 2018/5/22
 **/
public class Test {


    public final static String HOST = "127.0.0.1";

    public final static int PORT = 9300; //http请求的端口是9200，客户端是9300

    public static  void addIndex1(String index, String type, String id, String  name, Date date, String level, String msg) throws IOException {

        TransportClient client = null;
        /**
         * 连接信息
         */
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));


        IndexResponse response = client.prepareIndex(index, type, id).setSource(XContentFactory.jsonBuilder()
                .startObject().field("type", name)
                .field("sendDate", date)
                .field("msg", msg)
                .field("level",level)
                .endObject()).get();
        System.out.println("索引名称:" + response.getIndex() + "\n类型:" + response.getType()
                + "\n文档ID:" + response.getId() + "\n当前实例状态:" + response.getVersion());


        /**
         * 关闭
         */
        if(null != client) {
            client.close();
        }
    }

    public static void getData1(String index, String type, String id)  throws IOException {
        TransportClient client = null;
        /**
         * 连接信息
         */
        client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName(HOST),PORT));

        GetResponse getResponse = client.prepareGet(index, type, id).get();
        System.out.println("索引库的数据:" + getResponse.getSourceAsString());
    }

    public static void main(String[] args) {
        try {
            addIndex1("教育","label","1","教育专区", new Date(), "1","在线教育 教育 培养 学生 教师 老师");
            getData1("教育", "label" ,"1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
