package com.guilherme.cursomc.resources;

import com.guilherme.cursomc.domain.OrderEntity;
import com.guilherme.cursomc.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> find(@PathVariable Integer id) {
        OrderEntity order = orderService.find((id));
        return ResponseEntity.ok().body(order);
    }

}
