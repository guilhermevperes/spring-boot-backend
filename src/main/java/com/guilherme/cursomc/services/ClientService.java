package com.guilherme.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.guilherme.cursomc.DTO.ClientDTO;
import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.services.exceptions.DataIngretyException;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Client: " + id + ". Não encontrado."));
    }

    public Client update(Integer id, Client client) {
        Client newObj = find(id);
        updateData(newObj, client);
        client.setId(id);
        return clientRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIngretyException("Não é possível porque há entidades relacionadas");
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDTO) {
        return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

}
