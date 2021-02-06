/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllers;

/**
 *
 * @author Veljko
 */

import fon.silab.FifaRangListServer.model.MatchType;
import fon.silab.FifaRangListServer.services.MatchTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/matchtypes")
@RestController
public class MatchTypeController {

    private final MatchTypeService matchTypeService;
    
    @Autowired
    public MatchTypeController(MatchTypeService matchTypeService) {
        this.matchTypeService = matchTypeService;
    }

    @GetMapping("")
    public List<MatchType> getAllMatchTypes() {
        return matchTypeService.getAllMatchTypes();
    }
}
