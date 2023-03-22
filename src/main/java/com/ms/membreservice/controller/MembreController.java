package com.ms.membreservice.controller;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.membreservice.entity.Membre;
import com.ms.membreservice.model.Invitation;
import com.ms.membreservice.service.InvitationFeignClient;
import com.ms.membreservice.service.MembreService;

@RestController
@RequestMapping("/membres")
public class MembreController {

    @Autowired
    private MembreService membreService;

    @Autowired
    private InvitationFeignClient invitationFeignClient;

    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
        try {
            //List<Invitation> invitations = this.invitationFeignClient.getInvitationsByMembreId(id);
            Membre membre =  membreService.findMembreById(id);
            //membre.setInvitations(invitations);
            return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                   .body(membre);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Membre>> getallMembre(){
        try {
            List<Membre> membres = this.membreService.findAllMembres();
            return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                   .body(membres);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    
}
