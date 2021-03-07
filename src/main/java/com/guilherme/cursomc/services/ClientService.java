package com.guilherme.cursomc.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.guilherme.cursomc.DTO.ClientDTO;
import com.guilherme.cursomc.DTO.ClientNewDTO;
import com.guilherme.cursomc.domain.Address;
import com.guilherme.cursomc.domain.City;
import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.domain.enums.ClientType;
import com.guilherme.cursomc.repositories.AddressRepository;
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
    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Client: " + id + ". Não encontrado."));
    }

    public Client insert(Client category) {
        category.setId(null);
        category = clientRepository.save(category);
        addressRepository.saveAll(category.getAddresses());
        return category;
    }

    public Client update(Integer id, Client client) {
        Client newObj = find(id);
        updateData(newObj, client);
        return clientRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIngretyException("Não é possível porque há pedidos relacionados");
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

    public Client fromDTO(ClientNewDTO objDTO) {
        Client client = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfCnpj(),
                ClientType.toEnum(objDTO.getType()));
        City city = new City(objDTO.getCityId(), null, null);
        Address address = new Address(null, objDTO.getAddress(), objDTO.getNumber(), objDTO.getComplement(),
                objDTO.getDistrict(), objDTO.getZipCode(), client, city);
        client.getAddresses().add(address);
        client.getCellphones().add(objDTO.getCellphone1());
        if (!Objects.isNull(objDTO.getCellphone2())) {
            client.getCellphones().add(objDTO.getCellphone2());
        }
        if (!Objects.isNull(objDTO.getCellphone3())) {
            client.getCellphones().add(objDTO.getCellphone3());
        }
        return client;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

}
