/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fon.silab.FifaRangListServer.exceptions.SelectionNotFoundException;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.services.CalculateRangList;
import fon.silab.FifaRangListServer.services.SelectionService;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/selections")
@RestController
@Validated
public class SelectionController {

    private final SelectionService selectionService;
    private final CalculateRangList calculateRangList;

    @Autowired
    public SelectionController(SelectionService selectionService, CalculateRangList calculateRangList) {
        this.selectionService = selectionService;
        this.calculateRangList = calculateRangList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSelection(@Valid @RequestBody Selection selection) {
        selectionService.save(selection);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSelection(@PathVariable(value = "id") int id) {
        selectionService.deleteSelection(id);
    }

    @GetMapping("{id}")
    public Selection getSelectionById(@PathVariable(value = "id") int id) {
        Selection selection = selectionService.getSelectionById(id);
        if (selection == null) {
            throw new SelectionNotFoundException("Selection with id " + id + " does not exist.");
        }
        return selection;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSelection(@PathVariable int id, @RequestBody String selectionString) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Selection selection = objectMapper.readValue(selectionString, Selection.class);

            selection.setHostMatches(getHostMatchesFromSelectionString(selectionString));
            selection.setAwayMatches(getAwayMatchesFromSelectionString(selectionString));
            selectionService.updateSelection(selection, id);
    }
    
    private List<Match> getHostMatchesFromSelectionString(String selectionString) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        String[] matchesString = selectionString.split("\"hostMatches\":");
        matchesString = matchesString[1].split("\"awayMatches\":");
        String hostMatchesString = matchesString[0];
        
        List<Match> hostMatches = Arrays.asList(objectMapper.readValue(hostMatchesString, Match[].class));
        return hostMatches;
    }

    private List<Match> getAwayMatchesFromSelectionString(String selectionString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] matchesString = selectionString.split("\"hostMatches\":");
        matchesString = matchesString[1].split("\"awayMatches\":");
        String awayMatchesString = matchesString[1];
        awayMatchesString = awayMatchesString.substring(0, matchesString[1].length() - 1);
        
        List<Match> awayMatches = Arrays.asList(objectMapper.readValue(awayMatchesString, Match[].class));
        return awayMatches;
    }

    @GetMapping("")
    public List<Selection> getAllSelections() {
        return selectionService.getAllSelections();
    }

    @GetMapping("calculate")
    public void calculateRangList() {
        calculateRangList.calculate();
    }

    @GetMapping("{id}/matches")
    public List<Match> getAllMatchesBySelection(@PathVariable int id) {
        return selectionService.getAllMatchesBySelection(id);
    }

}
