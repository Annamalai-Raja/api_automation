import com.aventstack.chaintest.plugins.ChainTestListener;
import io.restassured.http.ContentType;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


@Listeners(ChainTestListener.class)
public class Serialization {


    @Test
 public void serialize(){
    Map<String , String> jsonBody= new HashMap<String , String>();

     List<String>  skills = new ArrayList<>();
     skills.add("Selenium");
     skills.add("Java");
     skills.add("Rest Assred");

    jsonBody.put("Name" , "Annamalai");
    jsonBody.put("Role" , "Automation Test Engineer");
    jsonBody.put("Skills" , String.valueOf(skills));

    System.out.println(jsonBody);

        given().baseUri("https://jsonplaceholder.typicode.com").contentType(ContentType.JSON).body(jsonBody).
                when().put("/albums/8").
                then().statusCode(200).log().all();
  }

}
