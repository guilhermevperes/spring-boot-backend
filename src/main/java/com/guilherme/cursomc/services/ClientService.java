package com.guilherme.cursomc.services;

import java.util.Optional;

import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Client: " + id + ". Não encontrado."));
    }

}
