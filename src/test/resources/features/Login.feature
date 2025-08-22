@Login
Feature: Login Feature

  Scenario: Login with valid credentials
    Given I am on the "LoginPage"
    When I enter username "admin" and password "admin123" on "Login" page
    And I click "loginButton" on "LoginPage" page

