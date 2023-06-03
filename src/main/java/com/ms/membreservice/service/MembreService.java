package com.ms.membreservice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.membreservice.repository.MembreRepository;
import com.ms.membreservice.entity.Membre;

@Service
public class MembreService {
    @Autowired
    private MembreRepository membreRepository;
    public Membre findMembreById(Long id) throws SQLException{
        if (this.membreRepository.findById(id).isPresent()){
            return this.membreRepository.findById(id).get();
        }else{
            return null;
        }
    }
    public List<Membre> findAllMembres() throws SQLException{
        //System.out.println(this.membreRepository.findAll().get(0).getPhoto());
        return this.membreRepository.findAll();
    }

    public Membre ajouterMembre(Membre m) throws SQLException{
        return this.membreRepository.save(m);
    }


    public void supprimerMembre(Long id){
        this.membreRepository.deleteById(id);
    }

    public Membre getMembreByEmail(String email) throws SQLException{
        return membreRepository.findByEmail(email);
    }

}
