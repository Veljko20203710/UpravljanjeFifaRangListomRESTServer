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
import fon.silab.FifaRangListServer.helpers.SimpleIDGenerator;
import fon.silab.FifaRangListServer.helpers.SimpleStringGenerator;
import fon.silab.FifaRangListServer.model.Confederation;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.model.User;


/**
 *
 * @author Veljko
 */
public class SelectionControllerUpdateOperationTest extends AbstractTest{
    

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void updateSelection() throws Exception {
       String uri = "/api/selections/"+UPDATE;
        Selection testSelection = new Selection();
        testSelection.setId(UPDATE);
        testSelection.setName(SimpleStringGenerator.generate(10));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(1);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0,testSelectionString.length()-1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(testSelectionString)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(204, status);
    }
    
    @Test
    public void updateSelectionWithNonexistentID() throws Exception {
       String uri = "/api/selections/"+INVALID_ID;
        Selection testSelection = new Selection();
        testSelection.setId(INVALID_ID);
        testSelection.setName(SimpleStringGenerator.generate(10));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0,testSelectionString.length()-1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(testSelectionString)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
    
    @Test
    public void updateSelectionWithNonUniqueName() throws Exception {
       String uri = "/api/selections/"+UPDATE;
        Selection testSelection = new Selection();
        testSelection.setId(UPDATE);
        testSelection.setName("Serbia");
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        String testSelectionString = super.mapToJson(testSelection);
        testSelectionString = testSelectionString.substring(0,testSelectionString.length()-1);
        testSelectionString = testSelectionString + ",\"hostMatches\":[],\"awayMatches\":[]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(testSelectionString)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
    }
 }

    

