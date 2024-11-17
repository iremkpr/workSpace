@ScrollDown
Feature: Scroll down continiously
 
   Scenario: Title of your scenario
   					Given Open the browser and navigate it https://the-internet.herokuapp.com/ site
   					When Click on the "Infinite Scroll" link
   					And Scroll down by 500 pixels 10 times with waiting 1 second between each scroll to the tag h3
   					Then Validate page scrolled Down
   					And Close the browser
   					