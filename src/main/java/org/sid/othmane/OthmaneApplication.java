package org.sid.othmane;


import org.sid.othmane.dao.ClientRepository;
import org.sid.othmane.dao.CompteRepository;
import org.sid.othmane.dao.OperationRepository;
import org.sid.othmane.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class OthmaneApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;




    public static void main(String[] args) {

     SpringApplication.run(OthmaneApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

       Client client1 = clientRepository.save(new Client("othmane","admin@gmail.com","admin"));
        Client client2 = clientRepository.save(new Client("imad","user@gmail.com","user"));
        Client client3 = clientRepository.save(new Client("medo","user1@gmail.com","user"));

        Compte compte1 = compteRepository.save(new CompteCourant(9000,6000,"c1",client1));
        Compte compte2 = compteRepository.save(new CompteCourant(2000,500,"c2",client2));
        Compte compte3 = compteRepository.save(new CompteEpargne(7800,2400,"c3",client3));
        /*
        *  this.active = users.getActive();
        this.email = users.getEmail();
        this.roles = users.getRoles();
        this.name = users.getName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
        *
        * */



        operationRepository.save(new Versement(new Date(),7000,compte1));
        operationRepository.save(new Versement(new Date(),2000,compte1));
        operationRepository.save(new Retrait(new Date(),3000,compte1));

        operationRepository.save(new Versement(new Date(),10000,compte2));
        operationRepository.save(new Versement(new Date(),15000,compte2));
        operationRepository.save(new Retrait(new Date(),7800,compte2));

        operationRepository.save(new Versement(new Date(),20000,compte3));
        operationRepository.save(new Versement(new Date(),12500,compte3));
        operationRepository.save(new Retrait(new Date(),30000,compte3));





    }
}
