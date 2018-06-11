package controller;

import domain.ResultRich;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * LogoutController
 * Create by cy on 2018/5/10
 **/
@RestController
public class LogoutController {

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public ResultRich logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return ResultRich.newInstance();
    }
}
