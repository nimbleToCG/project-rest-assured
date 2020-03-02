package com.example.projectrestassured.uc;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @ClassName LoginTestService
 * @Description TODO
 * @Author CG
 * @Date 2020/3/2 17:56
 * @Version 1.0
 **/
public class LoginTestService {
    /**
     * POST
     * 指定接口地址，获取登录用户的信息
     *
     * @param ApiUrl
     * @return UserDetail
     */
    public static String GetUserInfo(int ID, String ApiUrl, String Param) throws Exception {
//        Response response = given()
//                .header("did","iphone")
//                .
//                .log().all()
//                .request()
//                .body(Param)
//                .when()
//                .post(ApiUrl);
        String key = "did";
        String value = "iphone";
        Response response = (Response)((RequestSpecification)RestAssured.given().config(RestAssured.config().sslConfig((new SSLConfig()).relaxedHTTPSValidation())).contentType("application/x-www-form-urlencoded; charset=UTF-8").header(key,value).log().all()).request().body(Param).when().post(ApiUrl, new Object[0]);

        response.print();
        String Json = response.asString();
        return Json;
    }
}
