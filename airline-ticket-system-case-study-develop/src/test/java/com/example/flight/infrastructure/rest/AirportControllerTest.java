package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.AirportManager;
import com.example.flight.application.manager.AirportManagerTest;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirportManager airportManager;

    @InjectMocks
    private AirportController airportController;

    private final String json  = "{\n" +
            "  \"name\": \"IST\",\n" +
            "  \"iataCode\": \"Istanbul\"\n" +
            "}";

    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(airportController).build();
    }

    @Test
    public void testRetrieveAirport() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/airport/IST")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void testAddAirport() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/airport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }


}
