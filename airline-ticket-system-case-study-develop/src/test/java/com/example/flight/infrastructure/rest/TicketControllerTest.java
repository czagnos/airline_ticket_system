package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.MemberManager;
import com.example.flight.application.manager.TicketManager;
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
public class TicketControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketManager ticketManager;

    @InjectMocks
    private TicketController ticketController;

    private final String json  = "{\n" +
            "  \"memberUid\": \"John\",\n" +
            "  \"flightNumber\": \"Smith\",\n" +
            "  \"creditCardNumber\": \"1234564789825\"\n" +
            "}";

    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }


    @Test
    public void testRetrieveTicket() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/ticket/ABC123")
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

    @Test
    public void testCancelTicket() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/ticket/ABC123/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content("ABC123"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testBuyTicket() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }



}
