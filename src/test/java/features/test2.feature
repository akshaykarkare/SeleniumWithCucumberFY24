Feature: Gmail login

  @demo
  Rule: group 2

  Scenario Outline: Login to Gmail and land on home page
    Given Open "<URL>" URL
    Then click on search
    And quite
  Examples:
    | URL |
    | gmailUrl|


  Scenario: Login to Groww and land on home page
    Given Open "growwUrl" URL
    And quite

