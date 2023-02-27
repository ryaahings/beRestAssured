package Assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Utility {
    String bearerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTI3VDEzOjAzOjEzLjI1OFoiLCJ1c2VybmFtZSI6ImFyeWEuc2luZ2hAM3BpbGxhcmdsb2JhbC5jb20ifQ._d4X8UHs3wRDOnDwccdBv8z62T9-b7SBY5P0R7CbYPefi33daqSdQqqM_AISfaAFn29r9OAKKy-RxyXX2p4FEA";
    Response response;
    PostPOJO data = new PostPOJO();

    Response get(int id) {
        response = given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .get("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().extract().response();
        return response;
    }

    Response get() {
        response = given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .get("https://stage-api-engage.3pillarglobal.com/api/technologies/")
            .then().extract().response();
        return response;
    }
    void delete(int id) {
        given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200);
    }

    Response post() {
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("AureliaJS");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular like frameworks");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");
        data.setTech_type_id(3);

        response = given()
            .contentType("application/json")
            .headers("Authorization", "Bearer "+ bearerToken)
            .body(data)
            .when()
            .post("https://stage-api-engage.3pillarglobal.com/api/technologies/")
            .then().contentType(ContentType.JSON).
            extract().response();
        return response;
    }
}
