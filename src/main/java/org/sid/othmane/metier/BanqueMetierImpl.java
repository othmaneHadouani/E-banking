package org.sid.othmane.metier;

import org.sid.othmane.dao.ClientRepository;
import org.sid.othmane.dao.CompteRepository;
import org.sid.othmane.dao.OperationRepository;
import org.sid.othmane.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service // pour instancier spring au demarage et cette notaton fait surtout pour la couche meiter
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
    Random rand = new Random();

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Compte consulterCompte(String codeCpte) {
        Optional<Compte> compte = compteRepository.findById(codeCpte);
        if(compte.isPresent()){
            return compte.get();
        }
        else throw new RuntimeException( "Compte introuvable  ");

    }
    @Override
    public void verser(String codeCpte, double montant) {


        Compte compte =this.consulterCompte(codeCpte);
        operationRepository.save(new Versement(new Date(),montant,compte));
        compte.setSolde(compte.getSolde()+montant);
        compteRepository.save(compte);

    }

    @Override
    public void retirer(String codeCpte, double montant) {

        Compte compte =this.consulterCompte(codeCpte);
        double facilitesCaisse=0;
        if(compte instanceof CompteCourant)
            facilitesCaisse = ((CompteCourant) compte).getDecouvert();

        if ((compte.getSolde()+facilitesCaisse)<montant) throw new RuntimeException("solde insuffisant ");
        operationRepository.save(new Retrait(new Date(),montant,compte));
        compte.setSolde(compte.getSolde()- montant);
        compteRepository.save(compte);
    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {

        if ((codeCpte1.equals(codeCpte2))) throw new RuntimeException("meme Compte");
        this.retirer(codeCpte1,montant);
        this.verser(codeCpte2,montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {
        return operationRepository.listOperation(codeCpte,new PageRequest(page,size));
    }

    @Override
    public Client ajouterClient(Client client) {
         Client client1 = clientRepository.save(client);
        return client1;
    }

    @Override
    public Page<Client> listClients(String motCle ,int page, int size) {
        return clientRepository.chercherParMotCle(motCle,new PageRequest(page,size));
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Compte> ListComptes(String motCle, int page, int size) {
        return compteRepository.chercherParMotCle(motCle,new PageRequest(page,size));    }



    @Override
    public Optional<Compte> findCompteById(String code) {
        return compteRepository.findById(code);
    }

    @Override
    public Optional<Client> findClientById(Long code) {
        return clientRepository.findById(code);
    }

    @Override
    public List<Client> findAll() {
       return clientRepository.findAll();
    }

    @Override
    public void removeClient(Long code) {
        Optional<Client> client = clientRepository.findById(code);
        if(client.get().getComptes().isEmpty()){
            clientRepository.deleteById(code);
        }
        else throw new RuntimeException( "impossible le client a des comptes  ");


    }

    @Override
    public void removeCompte(String code) {
        Optional<Compte> comptes = compteRepository.findById(code);
        for(Operation operation :comptes.get().getOperations()){
            operationRepository.deleteById(operation.getNumOperation());
        }
        compteRepository.deleteById(code);
        
    }

    @Override
    public CompteEpargne addCompteEpargne(double solde, double taux,Long codeClient) {
        String str="";
        for(int i = 0 ; i < 2 ; i++){
            char c = (char)(rand.nextInt(26) + 97);
            str +=c;
        }
        Optional<Client> client =clientRepository.findById(codeClient);
        Client client1 =clientRepository.save(client.get());
       return compteRepository.save( new CompteEpargne(solde,taux,str,client1));
    }

    @Override
    public CompteCourant addCompteCourant(double solde, double decouvert,Long codeClient) {

        String str="";
        for(int i = 0 ; i < 2 ; i++){
            char c = (char)(rand.nextInt(26) + 97);
            str +=c;
        }
        Optional<Client> client =clientRepository.findById(codeClient);
        Client client1 =clientRepository.save(client.get());
       return compteRepository.save( new CompteCourant(solde,decouvert,str,client1));
    }

    @Override
    public Page<Compte> listComptesClient(Long code, int page, int size) {
        Client client =clientRepository.getOne(code);
       return compteRepository.findByClient(client,new PageRequest(page,size));
    }


}
