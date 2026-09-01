@wip
Feature: Booking API

  Scenario Outline: Create a new booking successfully
    Given user have valid booking under the name of "<firstname>" "<lastname>"
    When user search for bookings by "<firstname>" "<lastname>" "<totalprice>" "<checkin>" "<checkout>" "<additionalneeds>" "<depositpaid>"
    Then the search results should conatain atleast one booking
    
    

    Examples:
      | firstname | lastname | totalprice | checkin    | checkout   | additionalneeds | depositpaid | 
      | Jane      | Doe      | 200        | 2026-01-01 | 2026-01-05 | breakfast       |  true       |
      | John      | Smith    | 350        | 2026-02-10 | 2026-02-15 | lunch           |  false      |  
      | Amit      | Sharma   | 500        | 2026-03-01 | 2026-03-03 | dinner          |  true       |
      