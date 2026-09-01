@wip
Feature: Handling API failures gracefully

  Scenario: Booking service returns 500 error
    Given the booking service is down
    When user tries to create a booking
    Then the system should handle the error gracefully

  Scenario: Booking service times out
    Given the booking service is slow to respond
    When user tries to create a booking
    Then the request should fail with a timeout