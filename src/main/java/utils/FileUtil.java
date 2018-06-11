package utils;

import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentResponse;
import com.baidubce.services.doc.model.ReadDocumentResponse;

import java.io.File;

/**
 * FileUtil
 * Create by cy on 2018/5/4
 **/
public class FileUtil {

    //通过本地文档创建
    public String  createDocument(DocClient client, File file, String title, String format, String notification, String access, String targetType) {
        CreateDocumentResponse resp = client.createDocument(file, title, format, notification, access, targetType);
        System.out.println("documentId: " + resp.getDocumentId());
        return  resp.getDocumentId();
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

    public void createNotification(DocClient client, String name, String endpoint) {
        client.createNotification(name, endpoint);
    }
}
