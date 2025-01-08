package apiOctober.helpers;

import apiOctober.domain.Board;
import apiOctober.domain.Card;
import apiOctober.domain.CheckList;
import apiOctober.domain.CheckListItem;
import apiOctober.domain.List;
import lombok.Getter;
import lombok.Setter;

public class TestCaseContext {

  @Setter @Getter
  private static Board board;
  @Setter @Getter
  private static List list;
  @Setter @Getter
  private static Card card;
  @Setter @Getter
  private static CheckList checklist;
  @Setter @Getter
  private static CheckListItem checklistItem;

}
