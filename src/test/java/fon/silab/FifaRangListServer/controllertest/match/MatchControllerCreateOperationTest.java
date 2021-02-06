/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.match;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import fon.silab.FifaRangListServer.model.Confederation;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.MatchType;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.model.User;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Veljko
 */
public class MatchControllerCreateOperationTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void createMatch() throws Exception{
        String uri = "/api/matches";
        
        Match match = new Match();
        
        Confederation confederation = new Confederation();
        confederation.setId(1);

        Selection host = new Selection();
        host.setId(1);
        host.setConfederation(confederation);
        match.setHost(host);

        Selection away = new Selection();
        away.setId(3);
        away.setConfederation(confederation);
        match.setAway(away);
        

        match.setHostGoals(0);
        match.setAwayGoals(4);

        User user = new User();
        user.setId(1);
        match.setUser(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        Date date = simpleDateFormat.parse("2.2.2020.");
        match.setDate(date);

        MatchType matchType = new MatchType();
        matchType.setId(3);
        match.setMatchType(matchType);
        
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(super.mapToJson(match))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }
    
}
