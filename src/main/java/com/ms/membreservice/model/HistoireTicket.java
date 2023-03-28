package com.ms.membreservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoireTicket {
    private Long id;
    private String priorite;
    private Date dateDeb;
    private Date dateFin;
    private int effort;
    private int position;
}
