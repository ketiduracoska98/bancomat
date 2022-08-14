package com.example.bancomat.repository;

import com.example.bancomat.model.Banknote;
import org.springframework.data.repository.CrudRepository;

public interface BanknoteRepository extends CrudRepository<Banknote, Integer> {

}
