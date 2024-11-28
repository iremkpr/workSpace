@buzz
Feature: Share post on Buzz Social Board
  User should be able to share a post on buzz

  Background: 
    Given Login to the HRM site
 		When Click the buzz menu item
 		
    Scenario: User should be able to post a photo 
     		And Click the upload images and add image
    		When Click the Post button
    		Then Validate that the user share a new post
    		
    @updateStatus
    Scenario: User should be able to update status and post it
    		And Fill the What is you Mind text area
    		When Click the Post submit button
    		Then Validate that the user share a new status
    	
    		
		@posts   
    Scenario: User should be able to check Most liked posts
    		And Click the Most Liked Posts dropdown			
    		Then Select the user who named "Lisa Andrews (Past Employee)"
    		And Validate that the Most Liked post is opened