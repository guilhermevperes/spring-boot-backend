package com.guilherme.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.guilherme.cursomc.domain.Address;
import com.guilherme.cursomc.domain.Category;
import com.guilherme.cursomc.domain.City;
import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.domain.OrderEntity;
import com.guilherme.cursomc.domain.OrderItem;
import com.guilherme.cursomc.domain.Payment;
import com.guilherme.cursomc.domain.PaymentCard;
import com.guilherme.cursomc.domain.PaymentTicket;
import com.guilherme.cursomc.domain.Product;
import com.guilherme.cursomc.domain.State;
import com.guilherme.cursomc.domain.enums.ClientType;
import com.guilherme.cursomc.domain.enums.PaymentState;
import com.guilherme.cursomc.repositories.AddressRepository;
import com.guilherme.cursomc.repositories.CategoryRepository;
import com.guilherme.cursomc.repositories.CityRepository;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.repositories.OrderItemRepository;
import com.guilherme.cursomc.repositories.OrderRepository;
import com.guilherme.cursomc.repositories.PaymentRepository;
import com.guilherme.cursomc.repositories.ProductRepository;
import com.guilherme.cursomc.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
