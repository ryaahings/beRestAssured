package Assignment;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest extends Utility {
    //    Scenario1 : Pass the id in the DELETE request 
    @Test
    void deleteRequestWithId() {
        Response response = post();
        delete(response.jsonPath().get("id"));
    }

    //    Scenario2 : After deleted id 
    @Test
    void deleteRequestWithoutId() {
        Response response = post();
        int id = response.jsonPath().get("id");
        delete(id);
        given()
            .headers("Authorization", "Bearer " + bearerToken)
            .when()
            .delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(204);
    }
}
