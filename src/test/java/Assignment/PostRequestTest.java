package Assignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestTest extends Utility {

    //    Scenario1 : Pass the data in the body without “name” in the body
    @Test
    void postErrorName() {
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "React"};
        data.setTech_type_id(4);
        data.setDoc_link("example.com");
        data.setDescription("description example");
        data.setAssoc_tags(assocTag);
        data.setLogo(6);

        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .post("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().statusCode(400);
    }

    //    Scenario2 : Pass the data in the body without “tech_type_id”
    @Test
    void postErrorTechType() {
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "React"};
        data.setName("ReactJS");
        data.setDoc_link("example.com");
        data.setDescription("description example");
        data.setAssoc_tags(assocTag);
        data.setLogo(6);

        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .post("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().statusCode(400);
    }

    //    Scenario3 : Pass the correct data in all required fields in the body
    @Test
    void postTestSuccessful() {
        response = post();
        int id = response.jsonPath().get("id");
        Assert.assertEquals(response.getStatusCode(), 200);
        delete(id);
    }

}
