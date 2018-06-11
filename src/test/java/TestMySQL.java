import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *  * Created by zhouxx on 2018/4/10 19:08 .
 *   */
public class TestMySQL {

    private static final String DRIVER         = "com.mysql.jdbc.Driver";
    private static final String URL            = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=false";
    private static final String NAME           = "root";
    private static final String PASS           = "123456";

    public static void main(String[] args) {

        try {

            String sql = "select now() dt " ;
            Statement pStemt = null;
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = (Statement) con.createStatement();
            ResultSet rs = pStemt.executeQuery(sql);
            while(rs.next()){
                String dt = rs.getString(1);
                String dt1 = rs.getString("dt");
                System.out.println("dt:"+dt+" dt1："+dt1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}