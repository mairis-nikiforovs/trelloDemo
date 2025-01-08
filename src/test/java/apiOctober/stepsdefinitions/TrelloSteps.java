package apiOctober.stepsdefinitions;

import static apiOctober.constants.ProjectConstants.BOARD_ID;
import static apiOctober.constants.ProjectConstants.BOARD_NAME;

import apiOctober.client.TrelloClient;

import apiOctober.domain.Board;
import apiOctober.domain.Card;
import apiOctober.domain.CheckList;
import apiOctober.domain.CheckListItem;
import apiOctober.domain.List;
import apiOctober.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class TrelloSteps {

  //Import: Option+Enter / Alt+Enter
  @Given("The board exists and has the correct information")
  public void getBoardAndCheckInformation(){
    Response response = TrelloClient.getBoardInfo(BOARD_ID);
    Board board = response.as(Board.class);

    Assertions.assertThat(board.getId())
        .as("Checking if the board ID is correct")
        .isEqualTo(BOARD_ID);

    Assertions.assertThat(board.getName())
        .as("Checking that the board name is set to default value")
        .isEqualTo(BOARD_NAME);
  }

  @When("I change the board title to {string}")
  public void iChangeBoardTitle(String title){
    Response response = TrelloClient.changeBoardName(BOARD_ID,title);
    Board board = response.as(Board.class);
    TestCaseContext.setBoard(board);
  }

  @And("I check that the board name was updated to {string}")
  public void iCheckThatTheBoardNameWasUpdatedTo(String title) {
    Board board = TestCaseContext.getBoard();

    Assertions.assertThat(board.getName())
        .as("Checking if the board name was updated correctly")
        .isEqualTo(title);
  }

  @Then("I add a list with title {string} to the board")
  public void iAddAListWithTitleToTheBoard(String title) {
    Response response = TrelloClient.createNewList(BOARD_ID,title);
    List list = response.as(List.class);
    TestCaseContext.setList(list);

    Assertions.assertThat(list.getName())
        .as("Checking if the list was created with correct name")
        .isEqualTo(title);

    Assertions.assertThat(list.getIdBoard())
        .as("Checking if the list is created in the correct board")
        .isEqualTo(BOARD_ID);
  }

  @When("I add a card with title {string} to the list")
  public void iAddACardWithTitleToTheList(String title) {
    Response response = TrelloClient.createCard(TestCaseContext.getList().getId(),title);
    Card card = response.as(Card.class);
    TestCaseContext.setCard(card);

    Assertions.assertThat(response.getTime())
        .as("Checking if the response was sent back in 5s")
        .isLessThan(5000);
  }

  @And("I attach a checklist named {string} to the card")
  public void iAttachAChecklistNamedToTheCard(String title) {
    Response response = TrelloClient.createChecklist(TestCaseContext.getCard().getId(),title);
    CheckList checklist = response.as(CheckList.class);
    TestCaseContext.setChecklist(checklist);
  }

  @Then("I add an item named {string} to the checklist")
  public void iAddAnItemNamedToTheChecklist(String title) {
    Response response = TrelloClient.createChecklistItem(TestCaseContext.getChecklist().getId(),title);
    CheckListItem item = response.as(CheckListItem.class);
    TestCaseContext.setChecklistItem(item);
  }

  @When("I try creating a card named {word} without specifying a list for it")
  public void iTryCreatingACardWithoutSpecifyingAListForIt(String title) {
    Response response = TrelloClient.createCardWithoutList(
        TestCaseContext.getList().getId(),title
    );
    TestCaseContext.setResponse(response);
  }

  @Then("I see an error response returned")
  public void iSeeAnErrorResponseReturned() {
    Response response = TestCaseContext.getResponse();

    Assertions.assertThat(response.statusCode())
        .as("The status code of the response is 400")
        .isEqualTo(400);

    Assertions.assertThat(response.getBody().prettyPrint())
        .as("The expected error message is displayed")
        .contains("invalid value for idList");
  }
}
