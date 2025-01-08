package apiOctober.stepsdefinitions;

import static apiOctober.constants.ProjectConstants.BOARD_ID;
import static apiOctober.constants.ProjectConstants.BOARD_NAME;

import apiOctober.client.TrelloClient;
import apiOctober.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

  @Before
  public void beforeHook(){
    TrelloClient.changeBoardName(BOARD_ID, BOARD_NAME);
  }

  @After
  public void afterHook(Scenario scenario){
    boolean noDelete = scenario.getSourceTagNames().toString().contains("NoDelete");
    if(!noDelete){
      TrelloClient.deleteList(TestCaseContext.getList().getId());
    }
  }

}
