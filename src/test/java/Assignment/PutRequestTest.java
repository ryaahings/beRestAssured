package Assignment;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequestTest extends Utility {
    //    Scenario1 : Pass the data in the body without “name” 
    @Test
    void putWithoutName() {
        Response response = post();
        PostPOJO data = new PostPOJO();
        String[] assoc_tags = {"tags", "framework"};

        data.setAssoc_tags(assoc_tags);
        data.setDescription("description");
        data.setLogo(6);
        data.setDoc_link("aruleia.com");
        data.setTech_type_id(4);

        int id = response.jsonPath().get("id");
        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(400);
        delete(id);
    }

    //    Scenario2 : Pass the data in the body without “tech_type_id”
    @Test
    void putWithoutTechType() {
        response = post();
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("AureliaJS");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular like framework");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");
        data.setTech_type_id(3);

        int id = response.jsonPath().get("id");
        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(400);
        delete(id);
    }

    //    Scenario3 : Pass the Existing data (duplicate data) into the body
    @Test
    void putWithDuplicateValue() {
        response = post();
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("AureliaJS");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular like framework");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");
        data.setTech_type_id(3);

        int id = response.jsonPath().get("id");
        response = given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().extract().response();
        Assert.assertEquals(response.statusCode(), 400);
        delete(id);
    }

    //    Scenario4 : Update Name, and Description from existing data in the body
    @Test(dependsOnMethods = "postTestSuccessful")
    void putRequestToUpdateData() {
        Response response = post();
        int id = response.jsonPath().get("id");
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("Aurelia");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular or Vue like framework");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");
        data.setTech_type_id(3);

        response = given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then()
            .extract().response();

        Assert.assertEquals(response.statusCode(), 200);
        delete(id);
    }

    //    Scenario5 : After updating the data
    @Test(dependsOnMethods = "putRequestToUpdateData")
    void verificationOfUpdatedData() {
        Assert.assertEquals(response.jsonPath().get("name"), "Aurelia");
        Assert.assertEquals(response.jsonPath().get("description"), "Angular or Vue like framework");
        Assert.assertEquals(response.jsonPath().get("logo"), "6");
        Assert.assertEquals(response.jsonPath().get("tech_type_id"), "3");
        Assert.assertEquals(response.jsonPath().get("assoc_tags[0]"), "Framework");
        Assert.assertEquals(response.jsonPath().get("assoc_tags[1]"), "Aurelia");
        Assert.assertEquals(response.jsonPath().get("assoc_tags[2]"), "JSFramework");
    }
}
