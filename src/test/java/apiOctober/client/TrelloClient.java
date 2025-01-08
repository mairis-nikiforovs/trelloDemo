package apiOctober.client;


import static apiOctober.constants.ProjectConstants.API_KEY;
import static apiOctober.constants.ProjectConstants.API_TOKEN;

import apiOctober.helpers.TestCaseContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TrelloClient {

  private static RequestSpecification trelloSpec(){
    return RestAssured
        .given().log().all()
        .contentType(ContentType.JSON)
        .queryParam("key",API_KEY)
        .queryParam("token", API_TOKEN)
        .when()
        .baseUri("https://api.trello.com/1");
  }

  public static Response getBoardInfo(String boardId){
    return RestAssured
        .given(trelloSpec())
        .when()
        .get("/boards/" + boardId)
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response changeBoardName(String boardId, String name){
    return RestAssured
        .given(trelloSpec())
        .queryParam("name", name)
        .when()
        .put("/boards/" + boardId)
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response createNewList(String boardId, String title){
    return RestAssured
        .given(trelloSpec())
        .queryParam("name", title)
        .queryParam("idBoard", boardId)
        .when()
        .post("/lists")
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response deleteList(String id){
    return RestAssured
        .given(trelloSpec())
        .queryParam("value", true)
        .when()
        .put("/lists/" + id + "/closed")
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response createCard(String idList, String name){
    return RestAssured
        .given(trelloSpec())
        .queryParam("idList", idList)
        .queryParam("name", name)
        .when()
        .post("/cards")
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response createChecklist(String idCard, String name){
    return RestAssured
        .given(trelloSpec())
        .queryParam("name", name)
        .when()
        .post("/cards/" + idCard + "/checklists")
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }

  public static Response createChecklistItem(String idChecklist, String name){
    return RestAssured
        .given(trelloSpec())
        .queryParam("name", name)
        .when()
        .post("/checklists/" + idChecklist + "/checkItems")
        .then().log().all()
        .statusCode(200)
        .extract().response();
  }
}
