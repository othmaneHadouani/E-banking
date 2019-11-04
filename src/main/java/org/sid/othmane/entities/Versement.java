package org.sid.othmane.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {


    public Versement(Date dateOperation, double monatant,Compte compte) {
        super(dateOperation, monatant,compte);
    }

    public Versement() {
        super();
    }
}
