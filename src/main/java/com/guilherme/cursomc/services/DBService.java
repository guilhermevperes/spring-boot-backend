package com.guilherme.cursomc.services;

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
import com.guilherme.cursomc.domain.enums.Perfil;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDataBase() throws Exception {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrôcnico");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de Escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlncia", st1);
        City c2 = new City(null, "São Paulo", st2);
        City c3 = new City(null, "Campinas", st2);

        st1.getCities().addAll(Arrays.asList(c1));
        st2.getCities().addAll(Arrays.asList(c2, c3));
        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "46657888805", ClientType.PESSOAFISICA,
                pe.encode("12456"));
        cli1.getCellphones().addAll(Arrays.asList("564654654", "45465456"));

        Client cli2 = new Client(null, "Ana Costa", "guilherme.vperes@gmail.com", "72842940865",
                ClientType.PESSOAFISICA, pe.encode("12456"));
        cli2.addPerfil(Perfil.ADMIN);
        cli2.getCellphones().addAll(Arrays.asList("19981984964", "32570159"));

        Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38770212", cli1, c2);
        Address address3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "13098621", cli2, c2);

        cli1.getAddresses().addAll(Arrays.asList(address1, address2));
        cli2.getAddresses().addAll(Arrays.asList(address3));

        clientRepository.save(cli1);
        clientRepository.save(cli2);
        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        OrderEntity orderEntity1 = new OrderEntity(null, sdf.parse("30/09/2017 10:32"), cli1, address1);
        OrderEntity orderEntity2 = new OrderEntity(null, sdf.parse("10/10/2017 19:35"), cli1, address2);

        Payment payment1 = new PaymentCard(null, PaymentState.SETTLED, orderEntity1, 6);
        orderEntity1.setPayment(payment1);
        Payment payment2 = new PaymentTicket(null, PaymentState.PENDING, orderEntity2, sdf.parse("20/10/2017 00:00"),
                null);
        orderEntity2.setPayment(payment2);

        cli1.getOrders().addAll(Arrays.asList(orderEntity1, orderEntity2));

        orderRepository.saveAll(Arrays.asList(orderEntity1, orderEntity2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        OrderItem item1 = new OrderItem(orderEntity1, p1, 0.0, 1, 2000.0);
        OrderItem item2 = new OrderItem(orderEntity1, p3, 0.0, 2, 80.0);
        OrderItem item3 = new OrderItem(orderEntity2, p2, 100.0, 1, 800.0);

        orderEntity1.getItems().addAll(Arrays.asList(item1, item2));
        orderEntity2.getItems().addAll(Arrays.asList(item3));

        p1.getItems().addAll(Arrays.asList(item1));
        p2.getItems().addAll(Arrays.asList(item3));
        p3.getItems().addAll(Arrays.asList(item2));

        orderItemRepository.saveAll(Arrays.asList(item1, item2, item3));
    }

}
