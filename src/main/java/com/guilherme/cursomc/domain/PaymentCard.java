package com.guilherme.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.guilherme.cursomc.domain.enums.PaymentState;

@Entity
@JsonTypeName("paymentCard")
public class PaymentCard extends Payment {

    private static final long serialVersionUID = 1L;

    private Integer plotsNumber;

    public PaymentCard() {
    }

    public PaymentCard(Integer id, PaymentState state, OrderEntity order, Integer plotsNumber) {
        super(id, state, order);
        this.plotsNumber = plotsNumber;
    }

    public Integer getPlotsNumber() {
        return plotsNumber;
    }

    public void setPlotsNumber(Integer plotsNumber) {
        this.plotsNumber = plotsNumber;
    }

}
