package Assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Utility {
    String bearerToken;
    Response response;
    PostPOJO data = new PostPOJO();

    Response get(int id) {
        response = given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .get("<url>" + id)
            .then().extract().response();
        return response;
    }

    Response get() {
        response = given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .get("<url>")
            .then().extract().response();
        return response;
    }
    void delete(int id) {
        given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .delete("<url>" + id)
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
            .post("<url>")
            .then().contentType(ContentType.JSON).
            extract().response();
        return response;
    }
}
