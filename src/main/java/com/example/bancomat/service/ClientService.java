package com.example.bancomat.service;

import com.example.bancomat.model.Client;
import com.example.bancomat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public boolean checkIfClientExists(Client client) {

        return clientRepository.existsById(client.getCnp());
    }

    public Optional<Client> findClient(Integer cnp) {
        return clientRepository.findById(cnp);
    }
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }
}
