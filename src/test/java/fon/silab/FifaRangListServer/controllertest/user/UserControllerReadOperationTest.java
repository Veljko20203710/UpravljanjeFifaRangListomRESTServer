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
import fon.silab.FifaRangListServer.exceptions.BusyUsernameException;
import fon.silab.FifaRangListServer.model.User;

/**
 *
 * @author Veljko
 */
public class UserControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getUserById() throws Exception {
        String uri = "/api/users/" + USER_TEST;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User restUser = super.mapFromJson(content, User.class);
        User testUser = new User();
        testUser.setUsername("user_test");
        Assertions.assertEquals(testUser, restUser);
    }

    @Test
    public void getNonexistentUserById() throws Exception {
        String uri = "/api/users/" + INVALID_ID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("User with id " + INVALID_ID + " does not exists.", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void getUserByUsername() throws Exception {
        String uri = "/api/users/username";
        User testUser = new User();
        testUser.setUsername("user_test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(406, status);
        Assertions.assertTrue(mvcResult.getResolvedException() instanceof BusyUsernameException);
    }

    @Test
    public void getNonexistentUserByUsername() throws Exception {
        String uri = "/api/users/username";
        User testUser = new User();
        testUser.setUsername("test_user");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testUser))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }
}
