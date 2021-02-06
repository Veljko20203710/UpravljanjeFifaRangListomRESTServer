/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.selection;

import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static fon.silab.FifaRangListServer.controllertest.selection.PathVariable.*;

/**
 *
 * @author Veljko
 */
public class SelectionControllerDeleteOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void deleteSelection() throws Exception {
        String uri = "/api/selections/"+DELETE;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }
    
    @Test
    public void deleteNonExistentSelection() throws Exception {
        String uri = "/api/selections/"+INVALID_ID;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
    
    
}
