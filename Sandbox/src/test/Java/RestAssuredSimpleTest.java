import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RestAssuredSimpleTest {

  @Test
  public void givenWhenThenTest(){
      given()
          .when()
          .get("https://api.github.com/")
          .then()
          .statusCode(200);
  }
}
