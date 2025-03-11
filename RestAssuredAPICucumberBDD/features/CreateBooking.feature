Feature: Create Booking Details using POST API
User should be able to create the booking details

@create
Scenario Outline: validate post bookingAPI works correctly
	Given hit the url of post booking api endpoint
	When pass the url of create booking in the request
	And pass the request body of bookingAPI <fname>
	Then receive the response code as 200
	Examples:
		|fname       |
		|api testing |

@create
Scenario Outline: Verify the booking details are created
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then verify that the firstname for the passed bookingid is <FirstName>
	Examples:
		| FirstName 			 |
		| api testing      |		

	
	