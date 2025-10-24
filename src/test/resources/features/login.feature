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
      | username in uppercase       | STANDARD_USER   | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | SQL injection attempt       | ' OR '1'='1     | ' OR '1'='1  | Epic sadface: Username and password do not match any user in this service |

  Scenario: Validate login with extra space under credentials
    Given user navigates to SauceDemo login page
    When user enters " standard_user " and " secret_sauce "
    Then  user should see "Epic sadface: Username and password do not match any user in this service" error on login screen