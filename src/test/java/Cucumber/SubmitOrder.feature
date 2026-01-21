@tag
Feature: Purchase the order from Ecommerce Website
Use this to run the feature file


Background:
Given I landed on ecommerce page

@Regression
Scenario Outline: Positive Test of Purchasing the order
Given I logged in with username <name> and password <password>
When I add <productName> to cart
And Checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." confirmation message is displayed






Examples:

|name             |password|productName|
|har1234@gmail.com|Hari@123|ZARA COAT 3|




 