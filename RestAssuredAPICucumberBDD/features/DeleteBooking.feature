Feature: Delete Booking Details using DELETE API
User should be able to create the booking details

@delete
Scenario: Token Generation
	Given hit the url of token generation api endpoint
	When pass the url of token generation in the request
	And pass the request body of token generation
	Then receive the response code as 200
	
@delete
Scenario Outline:
: Delete Booking Details
	Given hit the url of delete booking details api endpoint
	When pass the url of delete booking api in the request <bookingId>
	Then receive the delete response code as 201
	Examples:
		| bookingId |
		| 126      |
	