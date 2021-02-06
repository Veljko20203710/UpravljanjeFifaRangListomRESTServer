/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;


import fon.silab.FifaRangListServer.model.Confederation;
import fon.silab.FifaRangListServer.repositories.ConfederationRepository;
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
public class ConfederationService {

    ConfederationRepository confederationRepository;

    @Autowired
    public ConfederationService(ConfederationRepository confederationRepository) {
        this.confederationRepository = confederationRepository;
    }
        
    public List<Confederation> getAllConfederation() {
        return confederationRepository.findAll();
    }

}
