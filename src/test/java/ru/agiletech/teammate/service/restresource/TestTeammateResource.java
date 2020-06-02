package ru.agiletech.teammate.service.restresource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.agiletech.teammate.service.Application;
import ru.agiletech.teammate.service.application.teammate.TeammateDTO;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
public class TestTeammateResource {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Test
    public void testFullFlow() throws Exception {
        var teammate = testPost();

        String id = teammate.getId();

        testPutFullName(id);
        testPutCredentials(id);
        testPutCredentials(id);
        testPutRole(id);
    }

    private TeammateDTO testPost() throws Exception {
        TeammateDTO teammateDTO = new TeammateDTO();

        teammateDTO.setEmail("test@mail.ru");
        teammateDTO.setLogin("login");
        teammateDTO.setPassword("password");
        teammateDTO.setName("Name");
        teammateDTO.setSurName("SurName");
        teammateDTO.setPatronymic("Patronymic");
        teammateDTO.setRole("USER");
        teammateDTO.setPhoneNumber("89261765544");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/teammates")
                .content(objectMapper.writeValueAsString(teammateDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        String content = mvcResult.getResponse()
                .getContentAsString();

        return objectMapper.readValue(content, TeammateDTO.class);
    }


    private void testPutFullName(String id) throws Exception {
        String name = "New Name";
        String surName = "New SurName";
        String patronymic = "New Patronymic";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/teammates/{id}/fullName?name={name}&surName={surName}&patronymic={patronymic}", id,
                name, surName, patronymic));
    }

    private void testPutCredentials(String id) throws Exception {
        String password = "newPassword";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/teammates/{id}/credentials?password={password}", id, password));
    }

    private void testPutContacts(String id) throws Exception {
        String email = "newtest@mail.ru";
        String phoneNumber = "89076785544";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/teammates/{id}/contacts?email={email}&phoneNumber={phoneNumber}", id,
                email, phoneNumber));
    }

    private void testPutRole(String id) throws Exception {
        String role = "ADMINISTRATOR";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/teammates/{id}/role?role={role}", id,
                role));
    }

}
