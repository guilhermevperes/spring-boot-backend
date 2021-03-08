package com.guilherme.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.guilherme.cursomc.domain.PaymentTicket;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    public void fillPaymentTicket(PaymentTicket paymentTicket, Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        paymentTicket.setDueDate(cal.getTime());
    }

}
