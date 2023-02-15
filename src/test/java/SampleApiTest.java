import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SampleApiTest {


//    ---------> PRACTICE WORK <-----------


    int id;
    private String bearerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAxLTI3VDE2OjQzOjM2Ljk3OVoiLCJ1c2VybmFtZSI6ImFyeWEuc2luZ2hAM3BpbGxhcmdsb2JhbC5jb20ifQ.dSPoqphVD7MWv1p0GpOIDzHsd2QLRbYMUrnML7YUYwVUa_n1Z0o-5IVAre-1VKBgKA1yN3eKaNbxF7yHQfSFdA";
    @Test
    void test1(){
        Response res = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Status code : "+ res.getStatusCode());
        System.out.println("Response Body : "+ res.getBody());

        int statCode = res.getStatusCode();
        Assert.assertEquals(statCode, 200, "Verify the status code is 200");

        given();
    }
    @Test
    void testGet(){
        given().headers("Authorization","Bearer " + bearerToken).
            when().get("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().statusCode(200).log().all();
    }

    @Test
    void testPost1(){
//        {"name":"Angular 19",
//            "tech_type_id":4,
//            "doc_link":"https://jsonformatter.org/",
//            "description":"description data",
//            "assoc_tags":["Framework","Angular"],
//            "logo":6}
        HashMap postData = new HashMap<>();
        postData.put("tech_type_id","4");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","6");

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies")

            .then().statusCode(400).log().all();
    }

    @Test
    void testPost2(){
        HashMap postData = new HashMap<>();
        postData.put("name","Angular 19");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","6");

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies")

            .then().statusCode(400).log().all();
    }

    @Test
    void testPost3(){
        //        get id in this one -- done
        HashMap postData = new HashMap<>();
        postData.put("name","Angular 19");
        postData.put("tech_type_id","4");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","6");

        id = given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies").jsonPath().getInt("id");

//            .then().statusCode(400).log().all();
    }

    @Test
    void testPut1(){
        HashMap postData = new HashMap<>();
        postData.put("tech_type_id","4");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","5");
//        changed something right above

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies/"+id)
//put id above ---- done
            .then().statusCode(400).log().all();
    }

    @Test
    void testPut2(){
        HashMap postData = new HashMap<>();
        postData.put("name","Angular 19");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","5");

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies/"+id)
//put id above
            .then().statusCode(400).log().all();
    }


    @Test
    void testPut3(){
        HashMap postData = new HashMap<>();
        postData.put("name","Angular 19");
        postData.put("tech_type_id","4");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","6");

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies")
//put id above
            .then().statusCode(400).log().all();
    }

    @Test
    void testPut4(){
        HashMap postData = new HashMap<>();
        postData.put("name","Angular xx");
        postData.put("tech_type_id","4");
        postData.put("doc_link","https://jsonformatter.org/");
        postData.put("description","description data is being changed");
        postData.put("assoc_tags","Framework");
        postData.put("assoc_tags","Angular");
        postData.put("logo","6");

        given().headers("Authorization", "Bearer " + bearerToken).contentType("application/json").body(postData)

            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies")
//put id above
            .then().statusCode(201).log().all();
    }

    @Test
    void testDelete(){
        given()

            .when().delete("https://stage-api-engage.3pillarglobal.com/api/technologies/"+id)

            .then().statusCode(204);
    }
}
