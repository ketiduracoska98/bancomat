package com.example.bancomat.repository;

import com.example.bancomat.model.Client;
import org.springframework.data.repository.CrudRepository;
public interface ClientRepository extends CrudRepository<Client, Integer> {

}
