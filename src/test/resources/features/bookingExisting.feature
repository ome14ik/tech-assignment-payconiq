@GetBooking
Feature: booking get endpoint

Scenario Outline: Getting all booking IDs
  Given the base URL - <URL> - is set
  When all booking IDs are requested
  Then the response status code should be <status code>
  And a list of booking IDs should be returned <id's exist>
  Examples:
    | URL                                | status code |  id's exist |
    |https://restful-booker.herokuapp.com| 200         |    true     |

  Scenario Outline: Getting all booking IDs related to  first name and last name
    Given the base URL - <URL> - is set
    And add <param 3> parameter and value <param 3 value>
    And add <param 4> parameter and value <param 4 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                |param 3  |param 3 value| param 4|param 4 value | status code |  id's exist  |
      |https://restful-booker.herokuapp.com|lastname| Allen        |firstname| Josh          | 200         |     true     |
      |https://restful-booker.herokuapp.com|lastname| Ala%n234    |firstname| R&oy23       | 200         |     false    |
      |https://restful-booker.herokuapp.com|lastname| Al$%an 23   |firstname| Josh          | 200         |     false    |
      |https://restful-booker.herokuapp.com|lastname| Allen        |firstname| R#$oy 23     | 200         |     false    |


  Scenario Outline: Getting all booking IDs related to last name
    Given the base URL - <URL> - is set
    And add <param 4> parameter and value <param 4 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                 | param 4|param 4 value | status code |  id's exist |
      |https://restful-booker.herokuapp.com |firstname| Josh          | 200         |     true    |
      |https://restful-booker.herokuapp.com |firstname| Ro#y2        | 200         |     false    |
      |https://restful-booker.herokuapp.com |firstname|              | 200         |     false    |


  Scenario Outline: Getting all booking IDs related to first name
    Given the base URL - <URL> - is set
    And add <param 3> parameter and value <param 3 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                 | param 3|param 3 value | status code |  id's exist |
      |https://restful-booker.herokuapp.com |lastname| Allen      | 200         |     true     |
      |https://restful-booker.herokuapp.com |lastname| A%32lan   | 200         |     false    |
      |https://restful-booker.herokuapp.com |lastname|           | 200         |     false    |

  Scenario Outline: Getting all booking IDs related to  checkin and checkout
    Given the base URL - <URL> - is set
    And add <param 1> parameter and value <param 1 value>
    And add <param 2> parameter and value <param 2 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                |param 1| param 1 value |param 2 | param 2 value   | status code |  id's exist     |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01  |checkout| 2019-01-01    | 200         |       true      |
      |https://restful-booker.herokuapp.com| checkin| 2019-06-2019  | checkout| 2019-06-2019  | 500         |       false     |
      |https://restful-booker.herokuapp.com| checkin|               | checkout|               | 500         |       false     |

  Scenario Template: Getting all booking IDs related to checkout
    Given the base URL - <URL> - is set
    And add <param 2> parameter and value <param 2 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                |param 2 | param 2 value | status code |  id's exist |
      |https://restful-booker.herokuapp.com| checkout| 2019-01-01    | 200         |       true      |
      |https://restful-booker.herokuapp.com| checkout| 2019-06-2019  | 500         |       false     |
      |https://restful-booker.herokuapp.com| checkout|               | 500         |       false     |

  Scenario Outline: Getting all booking IDs related to checkin
    Given the base URL - <URL> - is set
    And add <param 1> parameter and value <param 1 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                |param 1 | param 1 value | status code |  id's exist     |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | 200         |       true      |
      |https://restful-booker.herokuapp.com| checkin| 2019-06-2019  | 500         |       false     |
      |https://restful-booker.herokuapp.com| checkin|               | 500         |       false     |

  Scenario Outline: Getting all booking IDs related to  checkin, checkout, firstName and lastName
    Given the base URL - <URL> - is set
    And add <param 1> parameter and value <param 1 value>
    And add <param 2> parameter and value <param 2 value>
    And add <param 3> parameter and value <param 3 value>
    And add <param 4> parameter and value <param 4 value>
    When all booking IDs are requested
    Then the response status code should be <status code>
    And a list of booking IDs should be returned <id's exist>
    Examples:
      | URL                                |param 1 | param 1 value | param 2 | param 2 value| param 3 |param 3 value| param 4|param 4 value | status code |  id's exist |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | checkout| 2019-01-01  |lastname| Allen        |firstname| Josh          | 200         |       true     |
      |https://restful-booker.herokuapp.com| checkin| 2019-06-053    | checkout| 2019-01-01   |lastname| Allen        |firstname| Josh          | 500        |       false     |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | checkout| 2023-07-083   |lastname| Allen        |firstname| Josh          | 500        |     false       |
      |https://restful-booker.herokuapp.com| checkin| 05-026-2019    | checkout| 028-07-2023   |lastname| Allen        |firstname| Josh          | 500         |      false      |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | checkout| 2019-01-01  |lastname| Allen        |firstname| Roy@#          | 200       |      false       |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | checkout| 2019-01-01  |lastname| Alan%1       |firstname| Josh          | 200         |    false        |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01    | checkout| 2019-01-01   |lastname| Alan@#3    |firstname| Roy32#          | 200         |    false       |
      |https://restful-booker.herokuapp.com| checkin| 2017-01-01   | checkout| 2023-07-1208   |lastname| Alan@#3    |firstname| Roy32#          | 500         |    false       |
      |https://restful-booker.herokuapp.com| checkin| 2019-0216-05    | checkout| 2019-01-01   |lastname| Alan@#3    |firstname| Roy32#          | 500         |    false       |
      |https://restful-booker.herokuapp.com| checkin| 2019-0216-05    | checkout| 2023-0317-08   |lastname| Alan@#3    |firstname| Roy32#          | 500         |    false       |

#  Scenario Outline: Create new booking and Get all booking IDs related to it by firstName and lastName
#    Given the base URL - <URL> - is set
#    And add <param 1> parameter and value <param 1 value>
#    And add <param 2> parameter and value <param 2 value>
#    And add <param 3> parameter and value <param 3 value>
#    And add <param 4> parameter and value <param 4 value>
#    When all booking IDs are requested
#    Then the response status code should be <status code>
#    And a list of booking IDs should be returned <id's exist>
#    Examples:
#
#
#  Scenario Outline: Getting a specific booking
#Given the base URL is set
#When a specific booking with ID <bookingId> is requested
#Then the response status code should be 200
#And the booking details should be returned
#    Examples:
#      | bookingId |
