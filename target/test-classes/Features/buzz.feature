@buzz
Feature: Share post on Buzz Social Board
  User should be able to share a post on buzz

  Background: 
    Given Login to the HRM site
 
    Scenario: User should be able to post a photo 
    		When Click the buzz menu item
    		And Click the upload images and add image
    		When Click the Post button
    		Then Validate that the user share a new post