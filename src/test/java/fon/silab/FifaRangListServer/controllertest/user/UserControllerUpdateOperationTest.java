/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.user;


import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static fon.silab.FifaRangListServer.controllertest.user.PathVariables.*;
import fon.silab.FifaRangListServer.helpers.SimpleStringGenerator;
import fon.silab.FifaRangListServer.model.User;

/**
 *
 * @author Veljko
 */
public class UserControllerUpdateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void updateUser() throws Exception {
        String uri = "/api/users/"+UPDATE;
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }
    
    @Test
    public void updateUserWithNonUniqueUsername() throws Exception {
        String uri = "/api/users/"+UPDATE;
        User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword(SimpleStringGenerator.generate(6));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();;
        Assertions.assertEquals(400, status);
    }
    
    @Test
    public void updateUserWithShorUsernameAndPassword() throws Exception {
        String uri = "/api/users/"+UPDATE;
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(0));
        testUser.setPassword(SimpleStringGenerator.generate(0));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
    @Test
    public void updateUserWithShortUsername() throws Exception {
        String uri = "/api/users/"+UPDATE;
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(0));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
    @Test
    public void updateUserWithShortPassword() throws Exception {
        String uri = "/api/users/"+UPDATE;
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(0));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }

    @Test
    public void updateNonexistentUserById() throws Exception {
        String uri = "/api/users/"+INVALID_ID;
        User testUser = new User();
        testUser.setUsername(SimpleStringGenerator.generate(6));
        testUser.setPassword(SimpleStringGenerator.generate(6));
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("User with id "+INVALID_ID+" does not exists.", mvcResult.getResolvedException().getMessage());
    }

}
