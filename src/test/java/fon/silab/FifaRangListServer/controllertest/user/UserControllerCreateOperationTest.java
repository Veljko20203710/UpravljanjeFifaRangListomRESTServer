/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.user;


import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import fon.silab.FifaRangListServer.helpers.SimpleStringGenerator;
import fon.silab.FifaRangListServer.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Veljko
 */
public class UserControllerCreateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void createUser() throws Exception{
        String uri = "/api/users";
        User testUser = new User();
        String usernameAndPassword = SimpleStringGenerator.generate(8);
        testUser.setUsername(usernameAndPassword);
        testUser.setPassword(usernameAndPassword);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }
    
    
    @Test
    public void createUserWithBusyUsername() throws Exception{
        String uri = "/api/users";
        User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword("user_test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
    }
    
    @Test
    public void createUserWithShortUsernameAndPassword() throws Exception{
        String uri = "/api/users";
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
    @Test
    public void createUserWithShortUsername() throws Exception{
        String uri = "/api/users";
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword(SimpleStringGenerator.generate(4));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
    @Test
    public void createUserWithShortPassword() throws Exception{
        String uri = "/api/users";
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(4));
        testUser.setPassword("test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
}
