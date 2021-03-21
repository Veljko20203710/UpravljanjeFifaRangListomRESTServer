/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.user;


import fon.silab.FifaRangListServer.controllertest.AbstractTest;
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
public class UserControllerLoginOperationTest extends AbstractTest{
    
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
     @Test
    public void loginUser() throws Exception {
        String uri = "/api/users/login";
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
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
         User restUser = super.mapFromJson(content, User.class);
        Assertions.assertEquals(testUser, restUser);
    }

    @Test
    public void loginUserWrongUsername() throws Exception {
        String uri = "/api/users/login";
        User testUser = new User();
        testUser.setUsername("test_user");
        testUser.setPassword("user_test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void loginUserWrongPassword() throws Exception {
        String uri = "/api/users/login";
        User testUser = new User();
        testUser.setUsername("user_test");
        testUser.setPassword("test_user");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void loginUserWrongUsernameAndPassword() throws Exception {
        String uri = "/api/users/login";
        User testUser = new User();
        testUser.setUsername("test_user");
        testUser.setPassword("test_user");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(401, status);
        Assertions.assertEquals("Username or password are wrong. Please, try again.", mvcResult.getResolvedException().getMessage());
    }
}
