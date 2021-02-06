/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.selection;


import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import fon.silab.FifaRangListServer.helpers.SimpleIDGenerator;
import fon.silab.FifaRangListServer.helpers.SimpleStringGenerator;
import fon.silab.FifaRangListServer.model.Confederation;
import fon.silab.FifaRangListServer.model.Selection;
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
public class SelectionControllerCreateOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void createSelection() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(1);
        testSelection.setUser(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }
    
    @Test
    public void createSelectionWithNonUniqueName() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName("Serbia");
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
    }
    
    
    @Test
    public void createSelectionWithNoUser() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(6));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
    @Test
    public void createSelectionWithNonConfederation() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(5));
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
      @Test
    public void createSelectionWithNegativePoints() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        testSelection.setPoints(-1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
      @Test
    public void createSelectionWithNegativeRang() throws Exception{
        String uri = "/api/selections";
        Selection testSelection = new Selection();
        testSelection.setName(SimpleStringGenerator.generate(10));
        Confederation confederation = new Confederation();
        confederation.setId(SimpleIDGenerator.generate(5));
        testSelection.setConfederation(confederation);
        User user = new User();
        user.setId(4);
        testSelection.setUser(user);
        testSelection.setRang(-1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(testSelection))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(400, status);
        Assertions.assertTrue(mvcResult.getResolvedException().getMessage().contains("Validation failed"));
    }
    
}
