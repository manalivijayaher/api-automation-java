@wip
Feature: Booking API

  Scenario Outline: Create a new booking successfully
    Given user have valid booking details for "<firstname>" "<lastname>" "<totalprice>" "<checkin>" "<checkout>" "<additionalneeds>" "<depositpaid>"
    When user creates a booking
    Then the booking should be created successfully
    And the response should be fully valid
    And the response should contain a booking id
    

    Examples:
      | firstname | lastname | totalprice | checkin    | checkout   | additionalneeds | depositpaid | 
      | Jane      | Doe      | 200        | 2026-01-01 | 2026-01-05 | breakfast       |  true       |
      | John      | Smith    | 350        | 2026-02-10 | 2026-02-15 | lunch           |  false      |  
      | Amit      | Sharma   | 500        | 2026-03-01 | 2026-03-03 | dinner          |  true       |
      
      
 
    Scenario Outline: Full booking lifecycle - create, get, update, delete
    Given user have valid booking details for "<firstname>" "<lastname>" "<totalprice>" "<checkin>" "<checkout>" "<additionalneeds>" "<depositpaid>"
    When user creates a booking
    Then the booking should be created successfully
    When user fetches the created booking
    Then the fetched booking details should match what was created
    When user logs in as admin
    And user updates the booking total price to "<updatedprice>"
    Then the booking should be updated successfully
    When user deletes the booking
    Then the booking should be deleted successfully

    Examples:
      | firstname | lastname | totalprice | depositpaid  | checkin    | checkout   | additionalneeds  | updatedprice |
      | Alex      | Kim      | 300        | true         | 2026-04-01 | 2026-04-05 | dinner           | 450          |
      | Priya     | Rao      | 220        | false        | 2026-05-10 | 2026-05-12 | breakfast        | 275          |