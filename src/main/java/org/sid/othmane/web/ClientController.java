package org.sid.othmane.web;


import org.sid.othmane.entities.Client;
import org.sid.othmane.entities.Compte;
import org.sid.othmane.entities.Operation;
import org.sid.othmane.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private IBanqueMetier banqueMetier;



    @RequestMapping("/chercherClients")
    public String consulter(Model model ,
                            @RequestParam(name = "motCle",defaultValue = "") String motCle ,
                            @RequestParam(name = "size",defaultValue = "4")int size,
                            @RequestParam(name = "page",defaultValue = "0")int page  )
    {

        Page<Client> clients =banqueMetier.listClients("%"+motCle+"%",page,size);
            int[] pages = new int[clients.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("clients",clients);
            model.addAttribute("pageCurrent",page);
            model.addAttribute("size",size);
            model.addAttribute("motCle",motCle);


        return "clients";
    }
    @RequestMapping(value = "/saveClient" ,method = RequestMethod.POST)
    public String addClient(Model model ,@Valid Client client, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "fomAddClient";
        }
        Client client1 =banqueMetier.ajouterClient(client);
        model.addAttribute("client",client1);
        return "confirmation";
    }

    @RequestMapping(value = "/deleteClient" ,method = RequestMethod.GET)
    public String lolo(Model model ,Long code,int page,String motCle){

        try {
            banqueMetier.removeClient(code);
        }
        catch (Exception e){

            model.addAttribute("exception",e);
            return "redirect:/chercherClients?motCle="+motCle+"&page="+page+"&error="+e.getMessage();
        }
        return "redirect:/chercherClients?motCle="+motCle+"&page="+page;


    }
    @RequestMapping(value = "/addClient")
    public String addCompte(Model model){

        model.addAttribute("client",new Client());
    return "fomAddClient";
    }


    @RequestMapping(value = "/formEditClient")
    public String formEditProduit(Model model,@RequestParam(name = "code",defaultValue = "-1") Long code){

        Optional<Client> client =banqueMetier.findClientById(code);
       model.addAttribute("client",client.get());

        return "formEditClient";
    }


}
