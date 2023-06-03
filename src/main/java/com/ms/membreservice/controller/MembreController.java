package com.ms.membreservice.controller;

import java.sql.SQLException;
import java.util.List;

import com.ms.membreservice.model.HistoireTicket;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.membreservice.entity.Membre;

import com.ms.membreservice.service.MembreService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/membres")
public class MembreController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MembreService membreService;




    @PostMapping("/membre")
    public Membre ajouterMembre(@RequestBody Membre m){
        try {
            return this.membreService.ajouterMembre(m);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/{id}")
    public Membre getMembreById(@PathVariable Long id) {
        try {
            Membre membre =  membreService.findMembreById(id);
            return membre;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping()
    public List<Membre> getallMembre(){
        try {
            return this.membreService.findAllMembres();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null; 
        }
    }

    @GetMapping("/membre")
    public Membre getMembreByEmail(@RequestParam("email") String email) {
        try {
            return this.membreService.getMembreByEmail(email);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) throws SQLException {
        this.membreService.supprimerMembre(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{membreId}/histoiresTickets")
    public List<HistoireTicket> getHistoireTicketsByMembreId(@PathVariable Long membreId) {
        String url = "http://localhost:9999/gestion-histoire-ticket/histoireTickets/membre/" + membreId;
        return restTemplate.getForObject(url, List.class);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Membre> modifierMembre(@RequestBody Membre membre) throws SQLException {
        Membre membreSaved = membreService.ajouterMembre(membre);

        if (membreSaved == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(membreSaved);

    }
}
