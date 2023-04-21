package files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

    @Test(dataProvider= "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI= "http://216.10.245.166";
        String response= given().log().all()
            .header("Content-Type", "application/json")
            .body(payload.AddBook(isbn, aisle))
            .when().post("/Library/Addbook.php")
            .then().log().all()
            .assertThat().statusCode(200)
            .extract().response().asString();

        JsonPath js= ReUsableMethods.rawToJson(response);
        String id= js.get("ID");
        System.out.println(id);
    }

    @Test(dataProvider= "BooksData")
    public void deleteBook(String isbn, String aisle) {
        RestAssured.baseURI= "http://216.10.245.166";
        String response= given().log().all()
            .queryParam("ID", isbn + aisle)
            .when().post("/Library/DeleteBook.php")
            .then().log().all()
            .assertThat().statusCode(200)
            .extract().response().asString();
    }

    @DataProvider(name= "BooksData")
    public Object[][] getData() {
        return new Object[][] { { "sfdgs", "2738" }, { "giav", "8495" }, { "inabc", "9741" } };
    }

}
