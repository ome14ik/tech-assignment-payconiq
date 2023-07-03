@CreatingFeature
Feature: booking post endpoint
Scenario Outline: Creating a booking
Given the base URL - <Url> - is set
And a booking with the following details: First name - <firstName>, Last Name -<lastName>, total price - <totalPrice>, checkin date - <checkIn>, deposit paid - <depositPaid>, checkout date - <checkOut>, additional needs - <additionalNeeds>
When a booking is created
Then the response status code should be <status code>
And the booking details should be returned - <returned details>
  Examples:
    | Url                                | firstName | lastName | totalPrice | depositPaid | checkIn     | checkOut   | additionalNeeds |  status code |returned details|
    |https://restful-booker.herokuapp.com| Steule    | Bartust  |  344       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    |true             |
    |https://restful-booker.herokuapp.com| Steule    |  Bartust |  234.23     |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    |true            |
    |https://restful-booker.herokuapp.com| Steule    | Bartust  |  -462       |    true     | 2017-01-01  | 2019-01-01 | towel, sleepers  |     200    |true            |
