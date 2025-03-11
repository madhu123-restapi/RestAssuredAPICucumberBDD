Feature: Update Booking Details using PUT API
User should be able to create the booking details

@update
Scenario Outline: validate post bookingAPI works correctly
	Given hit the url of post booking api endpoint
	When pass the url of create booking in the request
	And pass the request body of bookingAPI <fname>
	Then receive the response code as 200
	Examples:
		|fname       |
		|api testing |

@update
Scenario Outline: Verify the booking details are correctly updated
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then verify that the firstname for the passed bookingid is <FirstName>
	Examples:
		| FirstName 			 |
		| api testing      |		
	
@update
Scenario: Token Generation
	Given hit the url of token generation api endpoint
	When pass the url of token generation in the request
	And pass the request body of token generation
	Then receive the response code as 200

@update	
Scenario Outline: validate put bookingAPI works correctly
	Given hit the url of put booking api endpoint
	When pass the url of booking api in the request
	And pass the request body of put bookingAPI <fname>
	Then receive the response code as 200
	Examples:
		|fname       |
		|Specflow    |
	