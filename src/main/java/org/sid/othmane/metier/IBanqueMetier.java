package org.sid.othmane.metier;

import org.sid.othmane.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBanqueMetier {

    public Compte consulterCompte(String codeCpte);
    public void verser (String codeCpte,double montant);
    public void retirer (String codeCpte,double montant);
    public void virement(String codeCpte1,String codeCpte2,double montant);
    public Page<Operation> listOperation (String codeCpte,int page,int size);
    public Client ajouterClient (Client client);
    public Page<Client> listClients(String motCle,int page,int size);
    public List<Client> listClients();
    public Page<Compte> ListComptes(String motCle, int page, int size);
    public Optional<Compte> findCompteById(String code);
    public Optional<Client> findClientById(Long code);
    public List<Client> findAll();
    public void removeClient(Long code);
    public void removeCompte(String code);
    public CompteEpargne addCompteEpargne(double solde , double taux, Long codeClient);
    public CompteCourant addCompteCourant(double solde, double decouvert, Long codeClient);
    public Page<Compte> listComptesClient(Long code,int page, int size);

}
