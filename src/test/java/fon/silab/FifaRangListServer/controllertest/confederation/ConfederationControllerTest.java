/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.confederation;

import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import fon.silab.FifaRangListServer.model.Confederation;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.assertj.core.util.Arrays;
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
public class ConfederationControllerTest extends AbstractTest {

   @Override
   @Before
   public void setUp() {
      super.setUp();
   }
    
    @Test
    public void getConfederations() throws Exception {
        String uri = "/api/confederations";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<Confederation> confederations = (List) Arrays.asList(super.mapFromJson(content, Confederation[].class));
        ConfederataionComparator confederataionComparator = new ConfederataionComparator();
        Collections.sort(confederations, confederataionComparator);
        
        List<Confederation> testConfederations = createAllConfederations();
        Collections.sort(testConfederations, confederataionComparator);
        Assertions.assertEquals(confederations,testConfederations);
    }
    
    private List<Confederation> createAllConfederations() {
        List<Confederation> confederations = new LinkedList<>();
               
        Confederation EUROPE = new Confederation();
        EUROPE.setId(1);
        EUROPE.setName("EUROPE");
        EUROPE.setStrenght(1);
        confederations.add(EUROPE);
       
        Confederation SOUTHAMERICA = new Confederation();
        SOUTHAMERICA.setId(2);
        SOUTHAMERICA.setName("SOUTHAMERICA");
        SOUTHAMERICA.setStrenght(1);
        confederations.add(SOUTHAMERICA);
        
        Confederation AFRICA = new Confederation();
        AFRICA.setId(3);
        AFRICA.setName("AFRICA");
        AFRICA.setStrenght(0.86);
        confederations.add(AFRICA);
        
        Confederation ASIA = new Confederation();
        ASIA.setId(4);
        ASIA.setName("ASIA");
        ASIA.setStrenght(0.86);
        confederations.add(ASIA);
        
        Confederation NORTHAMERICA = new Confederation();
        NORTHAMERICA.setId(5);
        NORTHAMERICA.setName("NORTHAMERICA");
        NORTHAMERICA.setStrenght(0.84);
        confederations.add(NORTHAMERICA);
        
        Confederation OCEANIA = new Confederation();
        OCEANIA.setId(6);
        OCEANIA.setName("OCEANIA");
        OCEANIA.setStrenght(0.84);
        confederations.add(OCEANIA);
        
        return confederations;
    }
    
    class ConfederataionComparator implements Comparator<Confederation> {

    @Override
    public int compare(Confederation a, Confederation b) {
        return a.getName().compareTo(b.getName());
        }
    }

}
