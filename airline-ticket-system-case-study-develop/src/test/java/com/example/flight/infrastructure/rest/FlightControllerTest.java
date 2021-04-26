package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.FlightManager;
import com.example.flight.application.manager.RouteManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightManager flightManager;

    @InjectMocks
    private FlightController flightController;

    private final String json  = "{\n" +
            "  \"flightNumber\": \"TK2021\",\n" +
            "  \"basePrice\": \"50\",\n" +
            "  \"capacity\": \"50\",\n" +
            "  \"companyCode\": \"TK\",\n" +
            "  \"routeUid\": \"5638a6f2-4de8-4d1c-9518-a7c3f8b0daae\"\n"+
            "}";
    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }


    @Test
    public void testRetrieveFlight() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/flight/TK2021")
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }


    @Test
    public void testAddAFlight() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }



}
