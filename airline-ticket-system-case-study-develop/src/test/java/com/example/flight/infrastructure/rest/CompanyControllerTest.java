package com.example.flight.infrastructure.rest;


import com.example.flight.application.manager.CompanyManager;
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
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompanyManager companyManager;

    @InjectMocks
    private CompanyController companyController;

    private final String json  = "{\n" +
            "  \"companyCode\": \"TK\",\n" +
            "  \"name\": \"Turk Hava Yollari\"\n" +
            "}";

    @Before
    public void setUp() throws  Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void testRetrieveCompany() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.get("/v1/company/TK")
                    .accept(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk());
    }

    @Test
    public void testAddACompany() throws Exception{

                mockMvc.perform(MockMvcRequestBuilders.post("/v1/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }


}
