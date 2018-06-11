package controller;

import domain.ErrorCode;
import domain.ResultRich;
import domain.ServerResponseEnvelope;
import domain.request.LoginReq;
import domain.response.LoginRes;
import model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.IAdminService;
import utils.MyProps;
import utils.SHA1Util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class LoginController {

    @Autowired
    IAdminService adminService;
    @Autowired
    MyProps myProps;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultRich<LoginRes> login(@RequestBody LoginReq loginReq, HttpServletRequest request){
        Admin admin = new Admin();
        admin.setPassword(loginReq.getPassword());
        admin.setUsername(loginReq.getUsername());
        ResultRich<LoginRes> resResultRich = new ResultRich<LoginRes>();
        File file = new File(myProps.getSecretKryPath());
        String secretKey = "";
        if(file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] b = new byte[1024];
                int len ;
                while((len = fileInputStream.read(b, 0, b.length)) != -1){
                    secretKey += new String(b);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        String password = "";
        try {
            byte[] bytes = SHA1Util.hexStrToByteArray(secretKey.trim());
            password = SHA1Util.encodeHmacSHA1(admin.getPassword().getBytes(),bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        admin.setPassword(password);
        try {
            ServerResponseEnvelope responseEnvelope = adminService.login(admin);
            if(!responseEnvelope.isSuccess()){
                resResultRich.setFail(ErrorCode.LOGIN_NAME_PASSWORD_WRONG);
            }else{
                LoginRes res = new LoginRes();
                Admin admin1 = (Admin) responseEnvelope.getSuccessResult();
                res.setUserId(admin1.getId());
                res.setUsername(admin1.getUsername());
                request.getSession().setAttribute("loginUser",res);
                resResultRich.setModel(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resResultRich;
    }





}
