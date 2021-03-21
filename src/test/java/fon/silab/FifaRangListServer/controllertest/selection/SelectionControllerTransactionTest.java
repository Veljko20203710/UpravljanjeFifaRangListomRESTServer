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
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.model.User;
import java.util.List;
import org.assertj.core.util.Arrays;

/**
 *
 * @author Veljko
 */
public class SelectionControllerTransactionTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void removeSelectionAndMatches() throws Exception {
        List<Match> matches = getMatchesOfSelection();
        Assertions.assertEquals(matches.size(), 2);
        deleteSelection();
        matches = getMatchesOfSelection();
        Assertions.assertEquals(matches.size(), 0);
    }

    private void deleteSelection() throws Exception {
        String uri = "/api/selections/" + TRANSACTION_SELECTION;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
    }

    private List<Match> getMatchesOfSelection() throws Exception {
        String uri = "/api/selections/" + TRANSACTION_SELECTION + "/matches";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Match> matches = (List) Arrays.asList(super.mapFromJson(content, Match[].class));
        return matches;
    }
    
}
