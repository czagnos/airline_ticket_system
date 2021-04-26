package com.example.flight.infrastructure.rest;


import com.example.flight.domain.model.entity.Airport;
import com.example.flight.domain.model.entity.Route;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.example.flight.application.manager.CompanyManager;
import com.example.flight.application.manager.RouteManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
public class RouteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RouteManager routeManager;

    @InjectMocks
    private RouteController routeController;

    private final String json  = "{\n" +
            "  \"origin\": \"IST\",\n" +
            "  \"destination\": \"SAW\"\n" +
            "}";

    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }


    @Test
    public void testRetrieveRoute() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/route/")
                    .param("origin","IST")
                    .param("destination","SAW")
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }



    @Test
    public void testAddARoute() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/route")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }



}
