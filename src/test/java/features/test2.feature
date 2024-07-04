Feature: Gmail login

  Rule: group 2
  @demo
  Scenario: Login to Gmail and land on home page
    Given Open "https://www.google.com/" URL
    Then click on search
    And quite


  Scenario: Login to Groww and land on home page
    Given Open "https://groww.in/" URL
    And quite

