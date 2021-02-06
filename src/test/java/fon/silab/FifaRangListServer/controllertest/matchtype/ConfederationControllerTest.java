/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.matchtype;

import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import fon.silab.FifaRangListServer.model.MatchType;
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
        String uri = "/api/matchtypes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<MatchType> matchTypes = (List) Arrays.asList(super.mapFromJson(content, MatchType[].class));
        MatchTypeComparator matchTypeComparator = new MatchTypeComparator();
        Collections.sort(matchTypes, matchTypeComparator);
        
        List<MatchType> testMatchTypes = createAllMatchTypes();
        Collections.sort(testMatchTypes, matchTypeComparator);
        Assertions.assertEquals(matchTypes,testMatchTypes);
    }
    
    private List<MatchType> createAllMatchTypes() {
        List<MatchType> matchTypes = new LinkedList<>();
               
        MatchType WORLDCUP = new MatchType();
        WORLDCUP.setId(1);
        WORLDCUP.setName("WORLDCUP");
        WORLDCUP.setStrenght(1);
        matchTypes.add(WORLDCUP);
        
        MatchType CONFEDERATIONCUP = new MatchType();
        CONFEDERATIONCUP.setId(2);
        CONFEDERATIONCUP.setName("CONFEDERATIONCUP");
        CONFEDERATIONCUP.setStrenght(0.8);
        matchTypes.add(CONFEDERATIONCUP);
        
        MatchType QUALIFIER = new MatchType();
        QUALIFIER.setId(3);
        QUALIFIER.setName("QUALIFIER");
        QUALIFIER.setStrenght(0.6);
        matchTypes.add(QUALIFIER);
        
        MatchType FRIENDLY = new MatchType();
        FRIENDLY.setId(4);
        FRIENDLY.setName("FRIENDLY");
        FRIENDLY.setStrenght(0.5);
        matchTypes.add(FRIENDLY);
        
       
        return matchTypes;
    }
    
    class MatchTypeComparator implements Comparator<MatchType> {

    @Override
    public int compare(MatchType a, MatchType b) {
        return a.getName().compareTo(b.getName());
        }
    }

}
