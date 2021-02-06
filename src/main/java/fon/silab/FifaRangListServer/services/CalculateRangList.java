/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;

import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.repositories.MatchRepository;
import fon.silab.FifaRangListServer.repositories.SelectionRepository;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Veljko
 */
@Service
@Transactional
public class CalculateRangList {
    
    SelectionRepository selectionRepository;
    MatchRepository matchRepository;

    @Autowired
    public CalculateRangList(SelectionRepository selectionRepository, MatchRepository matchRepository) {
        this.selectionRepository = selectionRepository;
        this.matchRepository = matchRepository;
    }
    
    
    
    public void calculate() {
        List<Selection> selections = selectionRepository.findAll();
        for (Selection selection : selections) {
            calcutePoints(selection);
        }
        updateRangList(selections);
    }
    
    private void calcutePoints(Selection selection) {
        List<Match> matches = matchRepository.findAllMatchesAway(selection.getId());
        matches.addAll(matchRepository.findAllMatchesHost(selection.getId()));
        int points = 0;
        for (Match m : matches) {
            points += checkWinner(m, selection) * checkImportance(m) * checkDate(m)
                    * checkOpponentStrength(m, selection) * checkConfederationStrength(m, selection);
        }
        selection.setPoints(points);
    }
    
    private double checkWinner(Match match, Selection selection) {
        if (match.getHost().equals(selection)) {
            return chechWinnerHost(match);
        } else {
            return chechWinnerAway(match);
        }
    }
    
    private double chechWinnerAway(Match match) {
        if (match.getHostGoals() < match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }
    
    private double chechWinnerHost(Match match) {
        if (match.getHostGoals() > match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }
    
    private double checkImportance(Match m) {
        return m.getMatchType().getStrenght();
    }
    
    private double checkDate(Match m) {
        Date date = new Date();
        
        if (m.getDate().getYear() == date.getYear()) {
            return MatchConstants.THISYEAR;
        } else if (m.getDate().getYear() + 1 == date.getYear()) {
            return MatchConstants.LASTYEAR;
        } else if (m.getDate().getYear() + 2 == date.getYear()) {
            return MatchConstants.TWOYEARSAGO;
        } else if (m.getDate().getYear() + 3 == date.getYear()) {
            return MatchConstants.THREEYEARSAGO;
        }
        return 0;
    }
    
    private double checkOpponentStrength(Match match, Selection selection) {
        return MatchConstants.INITIALOPPONENTSTRENGHT - getOpponent(match, selection).getRang();
    }
    
    private double checkConfederationStrength(Match match, Selection selection) {
        return getOpponent(match, selection).getConfederation().getStrenght();
    }
    
    private static Selection getOpponent(Match match, Selection selection) {
        if (selection.equals(match.getAway())) {
            return match.getHost();
        }
        return match.getAway();
    }
    
    private void updateRangList(List<Selection> selections) {
        selections = selections.stream()
                .sorted(Comparator.comparing(Selection::getPoints).reversed())
                .collect(Collectors.toList());
        int rang = 1;
        for (Selection selection : selections) {
            selection.setRang(rang++);
            selectionRepository.save(selection);
        }
    }
}
