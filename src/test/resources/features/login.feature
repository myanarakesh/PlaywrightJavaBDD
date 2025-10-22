Feature: Login functionality on SauceDemo

  Scenario: Valid login
    Given user navigates to SauceDemo login page
    When user enters valid credentials
    Then user should see the product inventory page