/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllertest.match;

import fon.silab.FifaRangListServer.controllertest.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.MatchType;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static fon.silab.FifaRangListServer.controllertest.match.PathVariables.*;

/**
 *
 * @author Veljko
 */
public class MatchControllerReadOperationTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getMatchByID() throws Exception {
        String uri = "/api/matches/" + GET_MATCH;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Match restMatch = super.mapFromJson(content, Match.class);
        Match testMatch = getMatch();
        Assertions.assertEquals(restMatch, testMatch);
    }
    
    @Test
    public void getNonexistentUserById() throws Exception {
        String uri = "/api/matches/"+INVALID_ID;
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(404, status);
        Assertions.assertEquals("Match with id "+INVALID_ID+" does not exists.", mvcResult.getResolvedException().getMessage());
    }

    private Match getMatch() throws ParseException {
        Match match = new Match();
        match.setId(1);

        Selection host = new Selection();
        host.setId(1);
        match.setHost(host);

        Selection away = new Selection();
        away.setId(3);
        match.setAway(away);

        match.setHostGoals(3);
        match.setAwayGoals(1);

        User user = new User();
        user.setId(1);
        match.setUser(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        Date date = simpleDateFormat.parse("1.1.2021.");
        match.setDate(date);

        MatchType matchType = new MatchType();
        matchType.setId(3);
        match.setMatchType(matchType);

        return match;
    }
}
