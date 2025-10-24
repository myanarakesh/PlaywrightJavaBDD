Feature: Home Page scenario for SauceDemo

  Background:
    Given user navigates to SauceDemo login page
    When user enters "standard_user" and "secret_sauce"
    Then user should see the product inventory page

    Scenario: Validate Hamburger menu after login
      And Validate menu items
