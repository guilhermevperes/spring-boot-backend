package com.guilherme.cursomc.services;

import java.util.Optional;

import com.guilherme.cursomc.domain.OrderEntity;
import com.guilherme.cursomc.repositories.OrderRepository;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity find(Integer id) {
        Optional<OrderEntity> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido: " + id + " não encontrado."));
    }

}
