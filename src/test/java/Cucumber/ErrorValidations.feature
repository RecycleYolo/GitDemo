@tag
Feature: Error Validation
Use this to run the feature file


@ErrorValidation
Scenario Outline: Positive Test of Purchasing the order
Given I landed on ecommerce page
When  I logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed



Examples:

|name             |password|
|har1234@gmail.com|Hari@13|