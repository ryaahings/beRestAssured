package Assignment;

import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTest extends Utility {
//  Scenario1 : Get the Technology data by hitting the API End Point
    @Test
    void getTechnologyData(){
        given()
            .headers("Authorization", "Bearer "+ bearerToken)
            .when()
            .get("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().log().all();
    }

//    Scenario2 : Verify mandatory fields
    @Test
    void verifyGetData(){
        Response response = post();
        int id =  response.jsonPath().get("id");
        response = get(id);
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("name")));
        Assert.assertTrue(StringUtils.isNotEmpty(response.jsonPath().get("tech_type_id").toString()));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("documentation_link")));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("tech_description")));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("associated_tags[0]")));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("associated_tags[1]")));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("associated_tags[2]")));
        Assert.assertTrue(StringUtils.isNotEmpty( response.jsonPath().get("tech_logo").toString()));
        delete(id);
    }
}