package Assignment;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteRequestTest extends Utility {
    int id;
    //    Scenario1 : Pass the id in the DELETE request
    @Test
    void deleteRequestWithId() {
        Response response = post();
        id = response.jsonPath().get("id");
        delete(id);
    }

    //    Scenario2 : After deleted id
    @Test(dependsOnMethods = "deleteRequestWithId")
    void deleteRequestWithoutId() {
        response = get(id);
        Assert.assertEquals(response.getBody().asString(),"Item not found");
    }
}
