package com.guilherme.cursomc.resources;

import java.net.URI;

import com.guilherme.cursomc.domain.OrderEntity;
import com.guilherme.cursomc.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> find(@Validated @RequestBody OrderEntity order) {
        OrderEntity obj = orderService.insert(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
