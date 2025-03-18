@testNG
@allure.label.epic:BookingAPI
Feature: End to End Booking Details
User should be able to create the booking details

@critical
Scenario Outline: 01-Validate post bookingAPI works correctly
	Given hit the url of post booking api endpoint
	When pass the url of create booking in the request
	And pass the request body of bookingAPI <fname>
	Then receive the response code as 200
	Examples:
		|fname       |
		|api testing |

@critical
Scenario Outline: 02-Verify the booking details are correctly updated
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then verify that the firstname for the passed bookingid is <FirstName>
	Examples:
		| FirstName 			 |
		| api testing      |		

@critical
Scenario: 03-Token Generation
	Given hit the url of token generation api endpoint
	When pass the url of token generation in the request
	And pass the request body of token generation
	Then receive the response code as 200

@critical
Scenario Outline: 04-Validate put bookingAPI works correctly
	Given hit the url of put booking api endpoint
	When pass the url of booking api in the request
	And pass the request body of put bookingAPI <fname>
	Then receive the response code as 200
	Examples:
		|fname       |
		|Specflow    |

@critical	
Scenario Outline: 05-Verify the booking details are correctly updated
	Given hit the url of get booking details api endpoint
	When pass the url of bookingid in the request
	Then verify that the firstname for the passed bookingid is <FirstName>
	Examples:
		| FirstName 				|
		| Specflow	      	|	

@critical	
Scenario: 06-Delete Booking Details
	Given hit the url of delete booking details api endpoint
	When pass the url of delete booking api in the request
	Then receive the delete response code as 201
	