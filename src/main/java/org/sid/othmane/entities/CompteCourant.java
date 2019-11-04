package org.sid.othmane.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;


@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant () {
        super();
    }

    public CompteCourant ( double solde,double decouvert,String codeCompte,Client client) {
        super(solde,codeCompte,client);
        this.decouvert=decouvert;
    }


    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
