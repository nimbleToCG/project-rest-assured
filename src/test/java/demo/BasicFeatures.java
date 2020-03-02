package demo;

import com.example.projectrestassured.uc.LoginTestService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @ClassName BasicFeatures
 * @Description TODO
 * @Author CG
 * @Date 2020/3/2 14:17
 * @Version 1.0
 **/
@SpringBootTest
public class BasicFeatures {
    /**
     * 简单检查响应状态码
     */
    @Test
    public void testStatusCode() {
        given().
                get("https://www.baidu.com").
                then().
                statusCode(200);
    }

    @Test
    public void test001Login() throws Exception{
        String loginType = "001";
        String userName = "18684691776";
        String loginPassword = "aaaa1111";
        int id = 1;
        String apiUrl = "http://10.36.4.185:9797/uc/api/login";
        String param = "loginType="+loginType+"&userName="+userName+"&loginPassword="+loginPassword;
        String userInfo = LoginTestService.GetUserInfo(id,apiUrl,param);
    }

}
