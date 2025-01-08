Feature: Trello test set

  @Scenario1
  Scenario: I change the board name and create a new list
    Given The board exists and has the correct information
    When I change the board title to "My custom board name"
    And I check that the board name was updated to "My custom board name"
    Then I add a list with title "My List" to the board

  @Scenario2
  Scenario: Create a list containing a card with a checklist
    Given I add a list with title "My list " to the board
    When I add a card with title "My card" to the list
    And I attach a checklist named "checklist" to the card
    Then I add an item named "TO DO 1" to the checklist
    And I add an item named "TO DO 2" to the checklist

  @Scenario3 @NoDelete
  Scenario: Create a list with 2 cards containing checklists
    Given I add a list with title "My list " to the board
    When I add a card with title "My card 1" to the list
    And I attach a checklist named "Checklist 1" to the card
    And I add an item named "TO DO 1" to the checklist
    Then I add a card with title "My card 2" to the list
    And I attach a checklist named "Checklist 2" to the card
    And I add an item named "TO DO 1" to the checklist
    And I add an item named "TO DO 2" to the checklist



