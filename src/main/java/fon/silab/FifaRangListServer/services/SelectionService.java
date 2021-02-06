/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;

import fon.silab.FifaRangListServer.exceptions.SelectionNotFoundException;
import fon.silab.FifaRangListServer.model.Match;
import fon.silab.FifaRangListServer.model.Selection;
import fon.silab.FifaRangListServer.repositories.MatchRepository;
import fon.silab.FifaRangListServer.repositories.SelectionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Veljko
 */
@Service
@Transactional
public class SelectionService {

    SelectionRepository selectionRepository;
    MatchRepository matchRepository;

    @Autowired
    public SelectionService(SelectionRepository selectionRepository, MatchRepository matchRepository) {
        this.selectionRepository = selectionRepository;
        this.matchRepository = matchRepository;
    }

    public void save(Selection selection) {
      selection.setRang(selectionRepository.findAll().size() + 1);
      selectionRepository.save(selection);
    }

    public void deleteSelection(int id) {
        selectionRepository.deleteById(id);
    }

    public Selection getSelectionById(int id) {
        return selectionRepository.findById(id).orElse(null);
    }

    public void updateSelection(Selection selection, int id) { 
        System.out.println(selectionRepository.findById(id));
        if(!selectionRepository.findById(id).isPresent()) throw new SelectionNotFoundException();
        selectionRepository.save(selection);
    }

    public List<Selection> getAllSelections() {
        return selectionRepository.findAll();
    }

    public List<Match> getAllMatchesBySelection(int id) {
        List<Match> matches = matchRepository.findAllMatchesHost(id);
        matches.addAll(matchRepository.findAllMatchesAway(id));
        return matches;
    }
}
