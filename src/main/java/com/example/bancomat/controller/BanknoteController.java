package com.example.bancomat.controller;

import com.example.bancomat.model.Banknote;
import com.example.bancomat.service.BanknoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banknote")
public class BanknoteController {
    @Autowired
    BanknoteService banknoteService;

    @PostMapping("/add")
    public ResponseEntity<Banknote> addBanknotes(@RequestBody Banknote banknote) {
        banknote.setCurrentQuantity(banknote.getInitialQuantity());
        banknote.setPercentage(banknote.calculatePercentage());
        banknoteService.addBanknote(banknote);
        return new ResponseEntity<>(banknote, HttpStatus.OK);
    }
}
