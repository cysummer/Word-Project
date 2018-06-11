package service.impl;

import dao.AdminMapper;
import domain.ServerResponseEnvelope;
import domain.request.AdminReq;
import model.Admin;
import model.AdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IAdminService;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements IAdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ServerResponseEnvelope<Admin> login(Admin admin) {
        ServerResponseEnvelope<Admin> result = new ServerResponseEnvelope<>();
        try {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPasswordEqualTo(admin.getPassword()).andUsernameEqualTo(admin.getUsername());
            List<Admin> a = adminMapper.selectByExample(adminExample);
            if(a.size() > 0)
                result.setSuccessResult(a.get(0));
            else
                result.setFailReasonStr("null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public ServerResponseEnvelope<Admin> update(AdminReq adminReq) {
        ServerResponseEnvelope<Admin> result = new ServerResponseEnvelope<>();
        try {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andUsernameEqualTo(adminReq.getUsername()).andPasswordEqualTo(adminReq.getPassword());
            List<Admin> a = adminMapper.selectByExample(adminExample);
            if(a.size() > 0){
                a.get(0).setPassword(adminReq.getNewPwd());
                adminMapper.updateByPrimaryKeySelective(a.get(0));
                result.setSuccessResult(a.get(0));
            }else
                result.setFailReasonStr("1");//原用户名密码不匹配
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailReasonStr("0");//系统错误
        }
        return result;
    }
}
