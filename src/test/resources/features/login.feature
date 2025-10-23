Feature: Login functionality on SauceDemo

  @login @Regression @sanity
  Scenario Outline: Validate login with valid credentials
    Given user navigates to SauceDemo login page
    When user enters "<username>" and "<password>"
    Then user should see the product inventory page
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @login @Regression
  Scenario Outline: Validate login with <scenario> credentials
    Given user navigates to SauceDemo login page
    When user enters "<username>" and "<password>"
    Then  user should see "<errorMessage>" error on login screen
    Examples:
      | scenario                    | username        | password     | errorMessage                                                              |
      | blocked account             | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | invalid                     | standard_user   | secret_sauc  | Epic sadface: Username and password do not match any user in this service |
      | empty username and password |                 |              | Epic sadface: Username is required                                        |
      | empty password              | standard_user   |              | Epic sadface: Password is required                                        |
      | empty username              |                 | secret_sauc  | Epic sadface: Username is required                                        |
