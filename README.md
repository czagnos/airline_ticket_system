# airline_ticket_system

This is a airline ticket system spring application.
This application runs with JSON reuqests.


# endpoints

All json samples are shared on "ticket_system.postman_collection.json" file on the repository. You can easily use with postman application.

1- Airport

  a- Get airport informations - GET /v1/airport/{iataCode}
  
        Error Exceptions: "Airport is not found"
        
  
  b- Add new airport          - POST /v1/airport
  
   Sample JSON Request:
                         {
                          "name" : "Istanbul",
                          "iataCode": "IST"
                      }
                      
2- Company
  
  a- Get company informations - GET /v1/company/{companyCode}
  
        Error Exceptions: "Company is not found"
  
  
  b- Add new company          - POST /v1/company  
  
     Sample JSON Request:
                         {
                          "name" : "THY",
                          "companyCode": "TK"
                          }
 
3- Route
  
  a- Get route informations, - GET /v1/route/
    to getting informations
    request parameters origin 
    and destinations should be
    added as you can see above.
    ![image](https://user-images.githubusercontent.com/30997410/116151328-24cff300-a6ed-11eb-96fd-c4f2b643ee1d.png)

        Error Exceptions: "Route is not found"
            
  
  b- Add new route          - POST /v1/route  
  
     Sample JSON Request:
                        {
                          "origin" : "SAW",
                          "destination": "IST"
                        }
 
4- Member
 
  a- Get member information  - GET /v1/member/{uid}
  
          Error Exceptions: "Member is not found"
                      
  b- Get all member list     - GET /v1/member/list
  
  c- Create new member       - POST /v1/member
  
       Sample JSON Request:
                        {
                            "identityNumber": "1234567890",
                            "firstName" : "John",
                            "surname": "Smith"
                        }
 
4- Flight

  a- Get flight information - GET /v1/flight/{flightNumber}
  
          Error Exceptions: "Flight is not found"
  
  b- Add new flight         - POST /v1/flight
  
  
         Sample JSON Request:
                        {
                            "flightNumber" : "TK2021",
                            "basePrice": "50",
                            "capacity":"10",
                            "companyCode":"TK",
                            "routeUid":"f1fb4356-58ec-4c2e-a93a-7ef479d7c196"
                        }
                        
4- Ticket

  a- Get ticket information - GET /v1/ticket/{pnrCode}
  
          Error Exceptions: "Ticket is not found"
  
  b- Buy ticket             - POST /v1/ticket
                  
          Sample JSON Request:
                        {
                            "memberUid" : "d47ab986-925c-4ac3-b605-208512b311a0",
                            "flightNumber": "TK2021",
                            "creditCardNumber": "1234-1234-1324-1324"
                        }
                        
          Error Exceptions: "Flight capacity is exceed", "Ticket is already bought by this member",
                            "Flight capacity is exceed".
                        
  c- Cancel ticket         - POST /v1/ticket/{pnrCode}/cancel
         
          Error Exceptions: "Ticket is already cancelled or not found"
  
# properties

1.  application.properties: H2 database created. Database will be deleted on every rerun appllication

2.  errorMessages.properties: Error message lists are used for throw exceptions on JSON response.
      
