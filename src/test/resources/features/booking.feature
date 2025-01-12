Feature: Search hotel
  Scenario: Looking for 'Viking Express Hotel'
    Given booking search page is opened
    When user searches for "Viking Express Hotel"
    Then "Viking Express Hotel" hotel is shown
    And hotel has rating "8,4"

