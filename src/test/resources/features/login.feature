Feature: Login functionality on SauceDemo

  Scenario Outline: Validate login with valid credentials
    Given user navigates to SauceDemo login page
    When user enters "<username>" and "<password>"
    Then user should see the product inventory page
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |