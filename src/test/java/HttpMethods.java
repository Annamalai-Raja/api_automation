import com.aventstack.chaintest.plugins.ChainTestListener;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

@Listeners(ChainTestListener.class)
public class HttpMethods {
    String endPoint = "https://jsonplaceholder.typicode.com";

  @Test
    public void getRequest(){
        given()
                .baseUri(endPoint)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void postRequests()  {
       /* JSONObject requestBody = new JSONObject();
        requestBody.put("userId", "8");
        requestBody.put("id", "9");
        requestBody.put("title" , "ABCD");*/
        File requestBody = new File("postData.json");

        given().baseUri(endPoint).header("Content-Type" , "application/json").body(requestBody).
                when().post("/albums").
                     then().statusCode(201).log().all();
    }

    @Test
    public void putRequests(){
       JSONObject requestBody = new JSONObject();

        requestBody.put("userId", "8.1");
        requestBody.put("id", "9.1");
        requestBody.put("title" , "ABCD updated");

        given().baseUri(endPoint).contentType(ContentType.JSON).body(requestBody.toString()).
                when().put("/albums/8").
                   then().statusCode(200).log().all();

    }


    @Test
    public void deleteRequests(){
       given()
                .baseUri(endPoint)
                .when()
                .delete("/albums/1")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

   // @Test
    public void getRepo(){
        given()
                .auth()
                .basic("username", "password")
                .when()
                .get("https://example.com/api/resource")
                .then()
                .statusCode(200);
    }

}
