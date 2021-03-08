package com.guilherme.cursomc.services;

import java.util.Date;
import java.util.Optional;

import com.guilherme.cursomc.domain.OrderEntity;
import com.guilherme.cursomc.domain.OrderItem;
import com.guilherme.cursomc.domain.PaymentTicket;
import com.guilherme.cursomc.domain.Product;
import com.guilherme.cursomc.domain.enums.PaymentState;
import com.guilherme.cursomc.repositories.OrderItemRepository;
import com.guilherme.cursomc.repositories.OrderRepository;
import com.guilherme.cursomc.repositories.PaymentRepository;
import com.guilherme.cursomc.repositories.ProductRepository;
import com.guilherme.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderEntity find(Integer id) {
        Optional<OrderEntity> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido: " + id + " não encontrado."));
    }

    public OrderEntity insert(OrderEntity order) {
        Date now = new Date();
        order.setId(null);
        order.setInstant(now);
        order.getPayment().setState(PaymentState.PENDING);
        order.getPayment().setOrder(order);
        if (order.getPayment() instanceof PaymentTicket) {
            PaymentTicket paymentTicket = (PaymentTicket) order.getPayment();
            ticketService.fillPaymentTicket(paymentTicket, now);
        }
        order = orderRepository.save(order);
        paymentRepository.save(order.getPayment());
        for (OrderItem oi : order.getItems()) {
            Optional<Product> product = productRepository.findById(oi.getProduct().getId());
            oi.setDiscount(0.0);
            oi.setPrice(product.get().getPrice());
            oi.setOrder(order);
            orderItemRepository.save(oi);
        }
        return order;
    }

}
