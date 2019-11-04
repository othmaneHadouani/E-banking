package org.sid.othmane.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@DynamicUpdate
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Long code;
    @NotNull
    @Size(min = 2,max = 50)
    private String nom;
    @OneToMany(mappedBy = "client")
    private Collection<Compte> comptes;
    @Column(name = "password")
    private String password;
    @Column(nullable = false, unique = true)
    @Email
    private String email;


    public Client(String nom, String email,String password) {
        this.nom = nom;
        this.email = email;
        this.password= password;

    }

    public Client() {
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }


}
