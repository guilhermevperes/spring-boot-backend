package com.guilherme.cursomc;

import java.util.Arrays;

import com.guilherme.cursomc.domain.Address;
import com.guilherme.cursomc.domain.Category;
import com.guilherme.cursomc.domain.City;
import com.guilherme.cursomc.domain.Client;
import com.guilherme.cursomc.domain.Product;
import com.guilherme.cursomc.domain.State;
import com.guilherme.cursomc.domain.enums.ClientType;
import com.guilherme.cursomc.repositories.AddressRepository;
import com.guilherme.cursomc.repositories.CategoryRepository;
import com.guilherme.cursomc.repositories.CityRepository;
import com.guilherme.cursomc.repositories.ClientRepository;
import com.guilherme.cursomc.repositories.ProductRepository;
import com.guilherme.cursomc.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlncia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "46657888805", ClientType.PESSOAFISICA);

		cli1.getCellphones().addAll(Arrays.asList("564654654", "45465456"));

		Address address1 = new Address(null, "address", "46", "casa", "xangrila", "13098-621", cli1, c1);
		Address address2 = new Address(null, "addressdfsafs", "46", "casa", "xangrila", "13098-621", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(address1, address2));

		clientRepository.save(cli1);
		addressRepository.save(address1);
		addressRepository.save(address2);
	}

}
