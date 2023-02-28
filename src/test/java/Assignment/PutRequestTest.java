package Assignment;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutRequestTest extends Utility {
    int id;

    @BeforeMethod
    public void setUpMethod() {
        response = post();
    }

    //    Scenario1 : Pass the data in the body without “name” 
    @Test
    void putWithoutName() {
        PostPOJO data = new PostPOJO();
        String[] assoc_tags = {"tags", "framework"};

        data.setAssoc_tags(assoc_tags);
        data.setDescription("description");
        data.setLogo(6);
        data.setDoc_link("aruleia.com");
        data.setTech_type_id(4);

        id = response.jsonPath().get("id");
        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("<url>" + id)
            .then().statusCode(400);
    }

    //    Scenario2 : Pass the data in the body without “tech_type_id”
    @Test
    void putWithoutTechType() {
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("AureliaJS");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular like framework");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");

        id = response.jsonPath().get("id");
        given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("<url>" + id)
            .then().statusCode(400);
    }

    //    Scenario3 : Pass the Existing data (duplicate data) into the body
    @Test
    void putWithDuplicateValue() {
        PostPOJO data = new PostPOJO();
        String[] assocTag = {"Framework", "Aurelia", "JSFramework"};

        data.setName("AureliaJS");
        data.setAssoc_tags(assocTag);
        data.setDescription("Angular like framework");
        data.setLogo(6);
        data.setDoc_link("aurelia.com");
        data.setTech_type_id(3);

        id = response.jsonPath().get("id");
        response = given()
            .contentType("application/json")
            .headers("Authorization", "Bearer " + bearerToken)
            .body(data)
            .when()
            .put("<url>" + id)
            .then().extract().response();
        Assert.assertEquals(response.statusCode(), 409);
    }

    //    Scenario4 : Update Name, and Description from existing data in the body
    @Test
    void putRequestToUpdateData() {
        id = response.jsonPath().get("id");
        updateData(id);
        Assert.assertEquals(response.statusCode(), 200);
    }

    //    Scenario5 : After updating the data
    @Test
    void verificationOfUpdatedData() {
        id = response.jsonPath().get("id");
        updateData(id);
        response = get(id);
        Assert.assertEquals(response.jsonPath().get("name"), "Aurelia");
        Assert.assertEquals(response.jsonPath().get("tech_description"), "Angular or Vue like framework");
        var techTypeLogo = response.jsonPath().get("tech_logo");
        Assert.assertEquals(techTypeLogo.toString(), "6");
        var techTypeId = response.jsonPath().get("tech_type_id");
        Assert.assertEquals(techTypeId.toString(), "3");
        Assert.assertEquals(response.jsonPath().get("associated_tags[0]"), "Framework");
        Assert.assertEquals(response.jsonPath().get("associated_tags[1]"), "Aurelia");
        Assert.assertEquals(response.jsonPath().get("associated_tags[2]"), "JSFramework");
        Assert.assertEquals(response.jsonPath().get("documentation_link"), "aurelia.com");
    }

    private void updateData(int id) {
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
            .put("<url>" + id)
            .then()
            .extract().response();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        delete(id);
    }
}
