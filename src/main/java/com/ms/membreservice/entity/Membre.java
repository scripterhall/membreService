package com.ms.membreservice.entity;

import java.util.List;

import com.ms.membreservice.model.Invitation;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="membre")
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Membre extends Personne{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Enumerated(EnumType.STRING) 
    private MembreStatus status ;

    @Transient
    private List<Invitation> invitations;

    @Column
    @Nullable
    private byte[] photo;
}
