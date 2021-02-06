/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;

import fon.silab.FifaRangListServer.model.MatchType;
import fon.silab.FifaRangListServer.repositories.MatchTypeRepository;
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
public class MatchTypeService {

    MatchTypeRepository matchTypeRepository;
    
    @Autowired
    public MatchTypeService(MatchTypeRepository matchTypeRepository) {
        this.matchTypeRepository = matchTypeRepository;
    }
    
    public List<MatchType> getAllMatchTypes() {
       return matchTypeRepository.findAll();
    }
    
}
