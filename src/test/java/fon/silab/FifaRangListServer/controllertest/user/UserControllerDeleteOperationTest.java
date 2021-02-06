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

/**
 *
 * @author Veljko
 */
public class UserControllerDeleteOperationTest extends AbstractTest {

   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   
    @Test
    public void deleteUser() throws Exception {
        String uri = "/api/users/"+DELETE;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }
    
    @Test
    public void deleteNonExistentUser() throws Exception {
        String uri = "/api/users/"+INVALID_ID;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
}
