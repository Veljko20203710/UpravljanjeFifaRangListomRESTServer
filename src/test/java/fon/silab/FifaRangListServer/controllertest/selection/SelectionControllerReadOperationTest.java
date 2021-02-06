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
import fon.silab.FifaRangListServer.model.Selection;

/**
 *
 * @author Veljko
 */
public class SelectionControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getSelectionById() throws Exception {
        String uri = "/api/selections/"+SELECTION_SERBIA;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Selection restSelection = super.mapFromJson(content, Selection.class);
        Selection testSelection = new Selection();
        testSelection.setId(1);
        testSelection.setName("Serbia");
        Assertions.assertEquals(testSelection, restSelection);
    }

    @Test
    public void getNonexistentSelectionById() throws Exception {
        String uri = "/api/selections/"+INVALID_ID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("Selection with id "+INVALID_ID+" does not exist.", mvcResult.getResolvedException().getMessage());
    }
}