import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentResponse;
import com.baidubce.services.doc.model.ReadDocumentResponse;

import java.io.File;

/**
 * Sample
 * Create by cy on 2018/5/2
 **/
public class DocTest {

    public static void main(String[] args) {
        String ACCESS_KEY_ID = "008d46fa7242449ea22e192e76ff8e83";
        String SECRET_ACCESS_KEY = "d86790d96a014880b09b75430d786713";
//        String ENDPOINT = "http://doc.bj.baidubce.com";

        // 初始化一个DocClient
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
//        config.setEndpoint(ENDPOINT);
        DocClient client = new DocClient(config);
//        Sample.getDocument(client, "doc-ie1rmnkqzzxx0uv");
//        Sample.readDocument(client, "doc-ie1rmnkqzzxx0uv");
        File file = new File("F:\\word_project\\1525426876137.doc");
        DocTest.createDocument(client, file, "我的标题","doc",null,null,null);
    }

    //通过本地文档创建
    public static  void createDocument(DocClient client, File file, String title, String format, String notification, String access, String targetType) {
        CreateDocumentResponse resp = client.createDocument(file, title, format, notification, access, targetType);
        System.out.println("documentId: " + resp.getDocumentId());
    }

    //通过文档的唯一标识 documentId 查询指定文档的详细信息
    public static  void getDocument(DocClient client, String documentId) {
        GetDocumentResponse resp = client.getDocument(documentId);
        System.out.println("documentId: " + resp.getDocumentId());
        System.out.println("title: " + resp.getTitle());
        System.out.println("format: " + resp.getFormat());
        System.out.println("status: " + resp.getStatus());
        if (resp.getStatus().equals("PUBLISHED")) {
            System.out.println("pageCount: " + resp.getPublishInfo().getPageCount());
            System.out.println("sizeInBytes: " + resp.getPublishInfo().getSizeInBytes());
            System.out.println("coverUrl: " + resp.getPublishInfo().getCoverUrl());
            System.out.println("publishTime: " + resp.getPublishInfo().getPublishTime());
        }
        if (resp.getStatus().equals("UPLOADING")){
            System.out.println("bucket: " + resp.getUploadInfo().getBucket());
            System.out.println("object: " + resp.getUploadInfo().getObject());
//            System.out.println("bosEndpoint: " + resp.getPublishInfo().getBosEndpoint());
        }
        if (resp.getStatus().equals("FAILED")){
            System.out.println("errorCode: " + resp.getError().getCode());
            System.out.println("errorMessage: " + resp.getError().getMessage());
        }
        System.out.println("notification: " + resp.getNotification());
        System.out.println("createTime: " + resp.getCreateTime());
    }

    public static void readDocument(DocClient client, String documentId) {
        ReadDocumentResponse resp = client.readDocument(documentId);
        System.out.println("documentId: " + resp.getDocumentId());
        System.out.println("host: " + resp.getHost());
        System.out.println("token: " + resp.getToken());
    }



}
