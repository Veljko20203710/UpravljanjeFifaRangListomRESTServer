/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;


import fon.silab.FifaRangListServer.exceptions.MatchNotFound;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.repositories.MatchRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Veljko
 */
@Service
@Transactional
public class MatchService {

    MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }
       
    public Match getMatchById(int id) {
        return matchRepository.findById(id).orElse(null);
    }

    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }

    public void updateMatch(Match updatedMatch, int id) {
        Match match = getMatchById(id);
        if(match == null) throw new MatchNotFound("Match with id "+id+" does not exists.");
        match.setAway(updatedMatch.getAway());
        match.setHost(updatedMatch.getHost());
        match.setHostGoals(updatedMatch.getHostGoals());
        match.setAwayGoals(updatedMatch.getAwayGoals());
        match.setMatchType(updatedMatch.getMatchType());
        match.setUser(updatedMatch.getUser());
        match.setDate(updatedMatch.getDate());
        matchRepository.save(match);
    }

    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

}
