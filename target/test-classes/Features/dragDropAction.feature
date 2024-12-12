@dropAndDrag
Feature: Drag the following blocks in different ways

  Background: 
    Given Open the https://demo.guru99.com/test/drag_drop.htm web site

  @tag1
  Scenario: User should be able to Drag the following blocks in different ways
			When Drag "Bank" object and drop "Debit Account" 
 			And Drag "Sales" object and drop "Credit Account"
 			And Drag "5000" object and drop "Debit Amount"
 			And Drag "5000" object and drop "Credit Amount"
 			