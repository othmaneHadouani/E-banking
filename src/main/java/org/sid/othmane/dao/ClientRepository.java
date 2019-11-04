package org.sid.othmane.dao;

import org.sid.othmane.entities.Client;
import org.sid.othmane.entities.Compte;
import org.sid.othmane.entities.Operation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {


    @Query("select c from Client c where c.nom like :x ")

    Page<Client> chercherParMotCle(@Param("x") String motCle,Pageable pageable);


    Optional<Client> findByEmail(String email);
}
