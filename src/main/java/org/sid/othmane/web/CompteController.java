package org.sid.othmane.web;


import org.sid.othmane.entities.*;
import org.sid.othmane.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CompteController {
    @Autowired
    private IBanqueMetier banqueMetier;


    @RequestMapping("/chercherCompte")
    public String consulter(Model model ,
                            @RequestParam(name = "motCle",defaultValue = "") String motCle ,
                            @RequestParam(name = "size",defaultValue = "4")int size,
                            @RequestParam(name = "page",defaultValue = "0")int page)
    {

        Page<Compte> comptes =banqueMetier.ListComptes("%"+motCle+"%",page,size);
        int[] pages = new int[comptes.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("comptes",comptes);
        model.addAttribute("pageCurrent",page);
        model.addAttribute("size",size);
        model.addAttribute("motCle",motCle);
        return "compteList";
    }
    @RequestMapping("/chercherCompteClient")
    public String consulterClient(Model model ,
                            @RequestParam(name = "code",defaultValue = "") Long code ,
                            @RequestParam(name = "size",defaultValue = "4")int size,
                            @RequestParam(name = "page",defaultValue = "0")int page)
    {

        Page<Compte> comptes =banqueMetier.listComptesClient(code,page,size);
        int[] pages = new int[comptes.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("comptes",comptes);
        model.addAttribute("pageCurrent",page);
        model.addAttribute("size",size);
        return "dodo";
    }
    @RequestMapping(value = "/saveCompte" ,method = RequestMethod.POST)
    public String addClient(Model model ,
                            Long codeClient,
                            CompteCourant compte,
                            String typeCompte){
        try{
            Optional<Client> client =banqueMetier.findClientById(codeClient);
            if(typeCompte.equals("courant")){
                CompteCourant compteCourant =banqueMetier.addCompteCourant(compte.getSolde(),compte.getDecouvert(),codeClient);
                model.addAttribute("compte",compteCourant);
            }
            else if (typeCompte.equals("epargne")){

               CompteEpargne compteEpargne =  banqueMetier.addCompteEpargne(compte.getSolde(),compte.getDecouvert(),codeClient);
                model.addAttribute("compte",compteEpargne);            }

            return "confirmationCompte";
        }
        catch (Exception e){
            return "redirect:/dodo";
        }
    }
    @RequestMapping(value = "/addCompte")
    public String addCompte(Model model){

        model.addAttribute("compte",new CompteCourant());
        model.addAttribute("clients",banqueMetier.listClients());
        model.addAttribute("client",new Client());
        return "fomAddCompte";
    }
    @RequestMapping(value = "/formEditCmpte")
    public String formEditCompte(Model model,String code){
        Optional<Compte> compte =banqueMetier.findCompteById(code);
        model.addAttribute("compte",compte.get());
        model.addAttribute("clients",banqueMetier.listClients());
        return "fomEditCompte";
    }
    @RequestMapping(value = "/deleteCompte" ,method = RequestMethod.GET)
    public String lolo(Model model ,String code,int page,String motCle){
        try {
            banqueMetier.removeCompte(code);
        }
        catch (Exception e){
            model.addAttribute("exception",e);
        }

        return "redirect:/chercherCompte?motCle="+motCle+"&page="+page;
    }


}
