package org.sid.othmane.dao;

import org.sid.othmane.entities.Client;
import org.sid.othmane.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,String> {

    @Query("select c from Compte c where c.codeCompte like :x ")
    Page<Compte> chercherParMotCle(@Param("x") String motCle,Pageable pageable);

    Page<Compte>  findByClient(Client client,Pageable pageable);

}
