package com.example.projectrestassured.uc;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

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
        Response response = given()
                .config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("form-data", ContentType.TEXT)))
                .contentType("form-data; charset=UTF-8")
                .header("did","iphone")
                .log().all()
                .request()
                .body(Param)
                .when()
                .post(ApiUrl);

        response.print();
        String Json = response.asString();
        return Json;
    }
}
