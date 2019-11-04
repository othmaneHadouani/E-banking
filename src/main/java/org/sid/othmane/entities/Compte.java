package org.sid.othmane.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte",discriminatorType = DiscriminatorType.STRING,length = 2)
public abstract class Compte implements Serializable {

    @Id
    private String codeCompte;
    private Date  dateCreation;
    @Min(100)
    private double solde;
    @ManyToOne
    @JoinColumn(name = "code_client")
    private Client client;
    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;

    public Compte(  double solde,String codeCompte,Client client) {
        this.codeCompte =codeCompte;
        this.dateCreation = new Date();
        this.solde = solde;
        this.client = client;
    }

    public Compte() {
    }

    public String getCodeCompte() {
        return codeCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public double getSolde() {
        return solde;
    }



    public Client getClient() {
        return client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }
}
