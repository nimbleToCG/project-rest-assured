package demo;

import com.example.projectrestassured.common.RequestValueService;
import com.example.projectrestassured.module.uc.LoginTestService;

import com.example.projectrestassured.utils.ExcelUtils;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static io.restassured.RestAssured.given;

/**
 * @ClassName BasicFeatures
 * @Description TODO
 * @Author CG
 * @Date 2020/3/2 14:17
 * @Version 1.0
 **/
@Slf4j
@SpringBootTest
public class BasicFeatures {

    @Resource
    RequestValueService requestValueService;

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
    public void test001Login() throws Exception {
        int id = 1;
        Map setParams = new HashMap();
        setParams.put("loginType", "001");
        setParams.put("userName", "18684691776");
        setParams.put("loginPassword", "aaaa1111");

        Map headersParams = new HashMap();
        headersParams.put("did", "iphone");

        String apiUrl = "http://10.36.4.185:9797/uc/api/login";
        String userInfo = LoginTestService.GetUserInfo(1, setParams, headersParams, apiUrl);
        log.info("返回结果：" + userInfo);
    }

    @Test
    public void LoginTestForExcel() throws Exception {

        String fileToBeRead = "F:\\dome_01.xlsx";//要打開的Excel的位置
        List dataList = ExcelUtils.readExcle(fileToBeRead);
        log.info("excle封裝的數據："+dataList);

//      for(int i=0;i<getRequestValue.list(s).size();i++){
//        //接口测试名i
//        getRequestValue.caseName(i);
//        //接口测试描述
//        getRequestValue.caseDescription(i);
//        //接口URI、端口
//        requestConfig.setHttpURIandPortValue(getRequestValue.baseURI(i),90);
//        //打印响应结果
//        Response response= assertRequestValue.assertApiParams(i);
//        response.prettyPrint();
//        System.out.println("\n\n");
//        //判断状态码是不是200
//        assertUtils.statusCode200(response);
//      }
    }

}

