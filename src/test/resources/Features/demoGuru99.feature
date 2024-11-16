@demoGuru99
Feature: Drag and Drop action's feature validation

  Scenario: User should be able Drag the following blocks in different ways
    Given Open the https://demo.guru99.com/test/drag_drop.htm web site
    When Drag "BANK" to "DEBIT SIDE"
    And Drag "SALES" to "CREDIT SIDE"
    And Drag 5000 to "DEBIT SIDE"
    And Drag 5000 to "CREDIT SIDE"
