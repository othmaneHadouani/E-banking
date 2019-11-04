package org.sid.othmane.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {


    private double taux;

    public CompteEpargne () {
        super();
    }
    public CompteEpargne ( double solde,double taux,String codeCompt,Client client) {

        super(solde,codeCompt,client);
        this.taux=taux;
    }


    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
