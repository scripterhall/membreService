package com.ms.membreservice.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ms.membreservice.model.HistoireTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.membreservice.entity.Membre;
import com.ms.membreservice.model.Invitation;
import com.ms.membreservice.service.InvitationFeignClient;
import com.ms.membreservice.service.MembreService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/membres")
public class MembreController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MembreService membreService;
    @Autowired
    private InvitationFeignClient invitationFeignClient;
    @GetMapping("/{id}")
    public Membre getMembreById(@PathVariable Long id) {
        try {
            List<Invitation> invitations = this.invitationFeignClient.getInvitationsByMembreId(id);
            Membre membre =  membreService.findMembreById(id);
            membre.setInvitations(invitations);
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
            return Collections.emptyList(); 
        }
    }
    @GetMapping("/{membreId}/histoiresTickets")
    public List<HistoireTicket> getHistoireTicketsByMembreId(@PathVariable Long membreId) {
        String url = "http://localhost:9999/gestion-histoire-ticket/histoireTickets/membre/" + membreId;
        return restTemplate.getForObject(url, List.class);
    }
}
