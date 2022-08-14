package com.example.bancomat.controller;

import com.example.bancomat.model.Banknote;
import com.example.bancomat.model.Client;
import com.example.bancomat.service.BanknoteService;
import com.example.bancomat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    BanknoteService banknoteService;

    @PostMapping("/sing-in")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        if (!clientService.checkIfClientExists(client)) {
            client.setCardNumber(client.generateCardNumber());
            clientService.addClient(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/withdraw/{cnp}")
    public List<Banknote> withdraw(@PathVariable Integer cnp, @RequestParam Integer sum) {
         Optional<Client> clientOptional = clientService.findClient(cnp);
         List<Banknote> banknotesToWithdraw = new ArrayList<>();
         if (clientOptional.isPresent()) {
             Client client = clientOptional.get();
             if (client.getAmount() >= sum) {
                 List<Banknote> allBanknotes = banknoteService.getBanknotes();
                 Collections.sort(allBanknotes);
                 banknotesToWithdraw = Banknote.withdrawBanknotes(sum, allBanknotes);
                 if (Banknote.isSumCorrect(sum, banknotesToWithdraw)) {
                     System.out.println(Banknote.aboveTwoHundred(sum));
                     for (int i = 0; i < banknotesToWithdraw.size(); i++) {
                         Banknote currentBanknote =  banknotesToWithdraw.get(i);
                         banknoteService.addBanknote(currentBanknote);
                         System.out.println(currentBanknote.alertMessage(client));
                     }
                     client.setAmount(client.getAmount() - sum);
                     clientService.updateClient(client);
                 }
                 else {
                     banknotesToWithdraw = null;
                 }
             }
         }
         return banknotesToWithdraw;
    }
}
