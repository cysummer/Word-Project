import domain.ServerResponseEnvelope;
import domain.request.UserReq;
import model.Admin;
import model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import service.IAdminService;
import service.IUserService;
import utils.MyProps;
import utils.PaginationQueryResult;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class TestService {

    @Autowired
    IAdminService adminService;
    @Autowired
    IUserService userService;

    @Autowired
    private MyProps myProps;

    @Test
    public void test1(){
        Admin admin = new Admin();
        admin.setPassword("1234");
        admin.setUsername("cy");
        UserReq userReq = new UserReq();
        userReq.setPageNum(1);
        userReq.setPageSize(20);
        try {
            ServerResponseEnvelope<PaginationQueryResult<Users>> list =  userService.list(userReq);
            PaginationQueryResult<Users> result =  list.getSuccessResult();
            List<Users> usersList = result.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        System.out.println(myProps.getFilePath());
    }

}
