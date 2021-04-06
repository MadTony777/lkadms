package lkadms;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Requests extends BaseClass {

    static List<String> request(String environment, String fileName) {
        String cid = String.valueOf(UUID.randomUUID());
        List<String> result = new ArrayList<>();
        Response response = RestAssured.given().log().all()
                .headers(getHeaders(fileName, cid))
                .get(getURL(environment, fileName)+ getOperation(fileName))
                .then()
                .statusCode(200)
                .extract()
                .response();
        String serviceResponse = response.body().asString();
        result.add(0, serviceResponse);
        assertREST(fileName, serviceResponse);
        System.out.println(serviceResponse);
        result.add(1, cid);
        return result;
    }




}
