package org.sid.othmane.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_operation",discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class Operation implements Serializable {


    @Id
    @GeneratedValue
    private Long numOperation;
    private Date dateOperation;
    private double monatant;
    @ManyToOne
    @JoinColumn(name="code_compte")
    private Compte compte;

    public Operation(Date dateOperation, double monatant,Compte compte) {
        this.compte=compte;
        this.dateOperation = dateOperation;
        this.monatant = monatant;
    }


    public Operation() {

    }
    public Long getNumOperation() {
        return numOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public double getMonatant() {
        return monatant;
    }

    public void setNumOperation(Long numOperation) {
        this.numOperation = numOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setMonatant(double monatant) {
        this.monatant = monatant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
