package service;

import domain.ServerResponseEnvelope;
import domain.request.DocumentReq;
import domain.response.DocumentRes;
import model.Documents;
import utils.PaginationQueryResult;

public interface IDocumentService {

    public ServerResponseEnvelope<PaginationQueryResult<DocumentRes>> list(DocumentReq  documentReq);

    public ServerResponseEnvelope<PaginationQueryResult<Documents>> documentUserlist(DocumentReq  documentReq);

    public ServerResponseEnvelope<DocumentRes> detail(int id);

    public ServerResponseEnvelope<DocumentRes> verify(int documentId, int status,  String  username);

    public ServerResponseEnvelope<Documents> add(Documents documents, String keyword);

    public ServerResponseEnvelope<Documents> select(Integer documentId);

    public ServerResponseEnvelope<Documents> read(Documents documents);

}
