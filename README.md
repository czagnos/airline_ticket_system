# airline_ticket_system

This is a airline ticket system spring application.
This application runs with JSON reuqests.


# endpoints


1- Airport
  a- Get airport informations - GET /v1/airport/{iataCode}
  
  
  b- Add new airport          - POST /v1/airport
   Sample JSON Request:
                         {
                          "name" : "Istanbul",
                          "iataCode": "IST"
                      }
                      ![image](https://user-images.githubusercontent.com/30997410/116150730-6d3ae100-a6ec-11eb-8da1-a64a1c5cdb94.png)

 
