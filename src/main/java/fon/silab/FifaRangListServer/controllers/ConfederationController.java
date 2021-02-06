/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.controllers;

import fon.silab.FifaRangListServer.model.Confederation;
import fon.silab.FifaRangListServer.services.ConfederationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/confederations")
@RestController
public class ConfederationController {

    ConfederationService confederationService;
    
    @Autowired
    public ConfederationController(ConfederationService confederationService) {
        this.confederationService = confederationService;
    }

    @GetMapping("")
    public List<Confederation> getAllConfederation() {
        return confederationService.getAllConfederation();
    }
}
