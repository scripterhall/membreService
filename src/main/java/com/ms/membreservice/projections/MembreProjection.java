package com.ms.membreservice.projections;

import com.ms.membreservice.entity.Membre;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="fullMember",types= Membre.class)
public interface MembreProjection {
}
