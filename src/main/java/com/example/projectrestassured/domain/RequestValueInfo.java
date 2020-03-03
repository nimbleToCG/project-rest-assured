package com.example.projectrestassured.domain;

import lombok.Data;

@Data
public class RequestValueInfo {
    private  Integer id;
    private  String module_name;
    private  String case_code;
    private  String case_name;
    private  String case_description;
    private  Integer is_run;
    private  String api_url;
    private  String path;
    private  String mothod;
    private  String param_type;
    private  String params;
}
