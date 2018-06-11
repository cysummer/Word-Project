package service;

import domain.ServerResponseEnvelope;
import domain.request.LabelReq;
import domain.request.UserReq;
import model.Labels;
import model.Users;
import utils.PaginationQueryResult;

import java.util.List;

public interface ILabelService {

    public ServerResponseEnvelope<PaginationQueryResult<Labels>> list(LabelReq labelReq);

    public ServerResponseEnvelope<List<Labels>> list();

    public ServerResponseEnvelope<Users> selectById(int id);

    public ServerResponseEnvelope  addLevel(LabelReq labelReq);

    public ServerResponseEnvelope  delete(int id);

    public ServerResponseEnvelope  update(LabelReq labelReq);
}
