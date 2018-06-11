package service;

import domain.ServerResponseEnvelope;
import domain.request.AdminReq;
import model.Admin;

public interface IAdminService {
    public ServerResponseEnvelope<Admin> login(Admin admin);

    public ServerResponseEnvelope<Admin> update(AdminReq adminReq);
}
