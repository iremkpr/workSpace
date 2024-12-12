@news
Feature: Published News In Anouncements

	Background: 
	   	  	Given Login to the HRM site
					Given Click the Anouncements Menu Item
					And Click the News Inner Menu Item
  @News
  Scenario: User should be able to search and open news
  					And Click Dinner Dance news
  					Then Validate that the news contains "We are glad to inform you that company dinner dance event will be held on 20th of July, 2021 at the office premises from 7:00pm onward." message
  					And Click the I acknowledge that I have read this news. checkbox
  					And Accept the pop-up 
  					Then Validate that the Succesfully Acknowladge