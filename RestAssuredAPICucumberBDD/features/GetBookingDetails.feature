Feature: Get Booking Details
User should be able to get the booking details

@get
Scenario: get the bookingids 
	Given hit the url of get booking ids api endpoint
	When pass the url of getbookingid in the request
	Then receive the response code as 200

@get
Scenario: Verify the booking details for a particular booking id
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then receive the response code as 200
	
@get
Scenario Outline: Verify the bookingid is present
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then verify that the firstname for the passed bookingid is <FirstName>
	Examples:
		| FirstName |
		| John      |