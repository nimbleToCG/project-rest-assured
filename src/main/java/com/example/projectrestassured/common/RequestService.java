package com.example.projectrestassured.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

  //get请求,不带headers参数
  public static Response setGet(Map setParams, String URI) {
    return RestAssured.given().params(setParams).when().get(URI);
  }

  //get请求,带headers参数
  public static Response setGet(Map setParams, Map headersParams, String URI) {
    return RestAssured.given().params(setParams).headers(headersParams).when().get(URI);
  }

  //发送post请求,不带headers参数
  public static Response setPost(Map setParams, String URI) {
    return RestAssured.given().params(setParams).when().post(URI);
  }

  //发送post请求,带headers参数
  public static Response setPost(Map setParams, Map headersParams, String URI) {
    return RestAssured.given().params(setParams).headers(headersParams).when().post(URI);
  }

}
