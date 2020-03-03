package com.example.projectrestassured.module.uc;

import com.example.projectrestassured.common.RequestService;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

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
   */
  public static String GetUserInfo(int ID, Map setParams, Map headersParams, String URI)
      throws Exception {
    Response response = RequestService.setPost(setParams, headersParams, URI);
    response.print();
    String Json = response.asString();
    return Json;
  }
}
