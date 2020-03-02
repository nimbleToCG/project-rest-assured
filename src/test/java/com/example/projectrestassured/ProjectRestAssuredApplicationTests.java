package com.example.projectrestassured;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
class ProjectRestAssuredApplicationTests {

    @Test
    void contextLoads() {
        given().
                get("https://www.baidu.com").
                then().
                statusCode(200);
    }

}
