import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Eclipse does not give auto suggestion for static libraries

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // validate if Add Place API is working as expected

        RestAssured.baseURI= "https://rahulshettyacademy.com";
        // given - all input details
        String response= given().log().all()
            .queryParam("key", "qaclick123")
            .header("Content-Type", "application/json")
            .body(payload.AddPlace())
            // when - submit the API - resource, http method
            .when().post("maps/api/place/add/json")
            // then - validate the response
            .then()
            .statusCode(200)
            .body("scope", equalTo("APP"))
            .header("server", "Apache/2.4.41 (Ubuntu)")
            .extract().response().asString();

        System.out.println(response);

        JsonPath js= new JsonPath(response); // for parsing Json
        String placeId= js.getString("place_id");

        System.out.println(placeId);

        // Add place -> Update place with new address
        // -> Get place to validate if new address is present in response

    }

}
