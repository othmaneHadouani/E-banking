package org.sid.othmane.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{


    public Retrait(Date dateOperation, double monatant,Compte compte) {
        super(dateOperation, monatant,compte);
    }

    public Retrait() {
        super();
    }


}
