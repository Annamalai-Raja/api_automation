import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(ChainTestListener.class)
public class TestAuth {

    @Test
    //Basic authentication involves sending the username and password encoded in Base64 in the request header.
    public void basicAuth(){
        String baseUri = "https://postman-echo.com";
        String username = "postman";
        String password = "password";

        given().baseUri(baseUri)
                .auth()
                .basic(username, password)
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    //Digest authentication is more secure than basic authentication as it encrypts the username and password.
    public void digestAuth(){
        String baseUri = "https://httpbin.org";
        String username = "user";
        String password = "passwd";

        given()
                .baseUri(baseUri)
                .auth()
                .digest(username,password)
                .when()
                .get("/digest-auth/auth/user/passwd")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    //Bearer tokens (often used in OAuth2) are sent in the Authorization header.
    public void bearerAuth(){
        String authKey = "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";
        String baseUri = "https://api.github.com";

        given()
                .baseUri(baseUri)
                .header("Authorization", "Bearer " + authKey)
                .when()
                .get("/user/repos")
                .then()
                .statusCode(200) // Ensure the token has access to repositories
                .log()
                .all();

    }

    @Test
    //OAuth 2.0 is widely used for secure token-based authorization.
    public void oAuth2(){
        String authkey = "3b99d669cdf34192a4df818b4d8775ddb63b48c0c900962bf5a1f1f6b20917c3";
        String baseuri = "https://gorest.co.in";

        given().baseUri(baseuri)
                .auth()
                .oauth2(authkey)
                .when()
                .get("/public/v2/users")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    //API keys are passed as query parameters or headers.
    public void apiKeyAuth() {
        String basuri = "https://newsapi.org/v2";
        String authkey = "10c0fb632ab742b5a5e925a588b24ee2";

        given()
                .baseUri(basuri)
                .queryParam("q" , "title")
                .queryParam("apiKey" , authkey)
                .when()
                .get("/everything")
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}

