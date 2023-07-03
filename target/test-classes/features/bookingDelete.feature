
Feature: Booking delete

  Scenario Outline: Delete a booking
Given the base URL - <Url> - is set
And use any existing - <existed id> - booking Id as parameter.
And a token is requested, with username - <userName> and password - <password>
When the booking id is deleted
Then the booking Id is successfully deleted - <booking id exist status code>

   Examples:
    | Url                                   | userName | password    | existed id    | booking id exist status code   |
    | https://restful-booker.herokuapp.com  | admin    | password123 |     true      |       404                      |

