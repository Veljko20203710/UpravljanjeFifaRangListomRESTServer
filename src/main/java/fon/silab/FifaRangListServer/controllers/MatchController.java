/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllers;


import fon.silab.FifaRangListServer.exceptions.MatchNotFound;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.services.MatchService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/matches")
@RestController
public class MatchController {
    
    private final MatchService matchService;
    
    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSelection(@Valid @RequestBody Match match) {
        matchService.saveMatch(match);
    }
    
    @GetMapping("{id}")
    public Match getMatchById(@PathVariable(value = "id") int id) {
        Match match = matchService.getMatchById(id);
        if(match == null) throw new MatchNotFound("Match with id " +id+ " does not exists.");
        return match;
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable(value = "id") int id) {
        matchService.deleteMatch(id);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMatch(@PathVariable int id, @RequestBody Match match) {
        matchService.updateMatch(match,id);
    }
    
    @GetMapping("")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
    
}
