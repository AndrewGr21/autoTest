Feature: 999.md

  Scenario: Login by customer
    Given user opens 'chrome' browser
    And user navigate to 'https://www.999.md/' url
    When user opens transports
    And user opens category transports
    And user scroll to checkbox Nissan
    And user click checkbox
    Then user save table in Excel document