package org.sid.othmane.web;


import org.sid.othmane.entities.Client;
import org.sid.othmane.entities.Compte;
import org.sid.othmane.entities.Operation;
import org.sid.othmane.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OperationController {
    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping("/")
    public String index1(){
        return "redirect:/operations";
    }
    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }
    @RequestMapping("/consulterCompte")
    public String consulter(Model model ,
                            String codeCompte ,
                            @RequestParam(name = "size",defaultValue = "3")int size,
                            @RequestParam(name = "page",defaultValue = "0")int page)
    {
        try{
            Compte compte = banqueMetier.consulterCompte(codeCompte);
            Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte,page,size);
            int[] pages = new int[pageOperations.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("size",size);
            model.addAttribute("codeCompte",codeCompte);
            model.addAttribute("listOperations",pageOperations.getContent());
            model.addAttribute("compte",compte);
            model.addAttribute("pageCurrent",page);
        }
        catch (Exception e){

            model.addAttribute("exception",e);
        }

        return "comptes";
    }
    @RequestMapping(value = "/saveOperetion" ,method = RequestMethod.POST)
    public String Save(Model model ,
                       String typeOperation,double montant,
                       String codeCompte2 ,
                       String codeCompte ){
        try{

            if(typeOperation.equals("virment")){

                banqueMetier.virement(codeCompte,codeCompte2,montant);

            }
            else if (typeOperation.equals("retrait")){

                banqueMetier.retirer(codeCompte,montant);
            }

            else if(typeOperation.equals("versment")){

                banqueMetier.verser(codeCompte,montant);

            }
        }
        catch (Exception e){

            model.addAttribute("error",e);
            return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
        }

        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }


}
