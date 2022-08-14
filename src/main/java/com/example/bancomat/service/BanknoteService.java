package com.example.bancomat.service;

import com.example.bancomat.model.Banknote;
import com.example.bancomat.repository.BanknoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BanknoteService {
    @Autowired
    BanknoteRepository banknoteRepository;
    public void addBanknote(Banknote banknote) {
        banknoteRepository.save(banknote);
    }

    public List<Banknote> getBanknotes() {
       return (List<Banknote>) banknoteRepository.findAll();
    }

}
