Feature: Booking partial update endpoint

Scenario Outline: Partially updating a booking
  Given the base URL - <Url> - is set
  And use any existing - <existing id> - booking Id as parameter.
  And a booking with the following details: First name - <firstName>, Last Name -<lastName>, total price - <totalPrice>, checkin date - <checkIn>, deposit paid - <depositPaid>, checkout date - <checkOut>, additional needs - <additionalNeeds>
  When a booking is partial updated
  Then the response status code should be <status code>
  And the updated booking details should be returned - <returned details>


  Examples:
| Url                                | firstName | lastName | totalPrice | depositPaid | checkIn     | checkOut   | additionalNeeds |  status code | existing id | returned details |
|https://restful-booker.herokuapp.com| Steule    | Bartust  |  344       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    | true         | true             |
|https://restful-booker.herokuapp.com| Steule    | Bartust  |  344       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    | true         | true             |
|https://restful-booker.herokuapp.com| Steule    | Bartust  |  344       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    | true         | true             |
|https://restful-booker.herokuapp.com| Steule    | Bartust  |  344       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    | true         | true             |
