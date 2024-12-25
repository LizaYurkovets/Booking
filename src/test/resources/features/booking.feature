Feature: Search hotel
  Scenario: Looking for 'Viking Express Hotel'
    Given booking search page is opened
    When user searches for "Viking Express Hotel"
    Then "Viking Express Hotel" hotel is shown

  Scenario Outline: Looking for 'Meraki'
    Given booking search page is opened
    When user searches for "<hotel>"
    Then "<expectedResult>" hotel is shown
    Examples:
      | hotel | expectedResult |
      | Viking Express Hotel | Viking Express Hotel
      | Meraki | Meraki Resort - Adults Only |
