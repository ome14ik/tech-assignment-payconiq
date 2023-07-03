@Token
Feature: Token verification with Cookies

  Scenario Outline:
    Given the base URL - <URL> - is set
    When a token is requested, with username - <userName> and password - <password>
    Then response status code should be <status code>
    And a token should be returned - <tokenValidation>

    Examples:
      | URL                                  | status code | userName | password    | tokenValidation |
      | https://restful-booker.herokuapp.com | 200         | admin    | password123 | true            |
      | https://restful-booker.herokuapp.com | 200         | username | 1234        | false           |
      | https://restful-booker.herokuapp.com | 200         | admin    | 1234        | false           |
      | https://restful-booker.herokuapp.com | 200         | username | password123 | false           |
