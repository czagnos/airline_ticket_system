package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.FlightManager;
import com.example.flight.application.manager.MemberManager;
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
public class MemberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MemberManager memberManager;

    @InjectMocks
    private MemberController memberController;

    private final String json  = "{\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"surname\": \"Smith\",\n" +
            "  \"identityNumber\": \"1234564789825\"\n" +
            "}";
    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }


    @Test
    public void testRetrieveMember() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/member/1234564789825")
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

    @Test
    public void testListMember() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/member/list")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMember() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }



}
