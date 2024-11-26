package com.d288.ryan.bootstrap;


import com.d288.ryan.dao.CustomerRepository;
import com.d288.ryan.dao.DivisionRepository;
import com.d288.ryan.entities.Customer;
import com.d288.ryan.entities.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private  final DivisionRepository divisionRepository ;
    private final  CustomerRepository customerRepository;

    @Autowired
    public BootStrapData(DivisionRepository divisionRepository, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;

    }

    @Override
    public void run(String... args) throws Exception {


        if (customerRepository.count() == 1) {
            Division southernOntario = new Division(69l, 2L, "Southern Ontario");

            Customer mikeKnarr = new Customer("Mike", "Knarr", "77 Street St", "N5X4N8", "5198527824", southernOntario);
            Customer paigeWebber = new Customer("Paige", "Webber", "78 Street St", "N5X4N8", "5198527826", southernOntario);
            Customer jeffOregan = new Customer("Jeff", "Oregan", "123 DingDong Ave", "N5X4N2", "5198527824", southernOntario);
            Customer andresHahn = new Customer("Andres", "Hahn", "984 Marigold St", "N5X4N8", "5198527824", southernOntario);
            Customer benProkopetz = new Customer("Ben", "Prokopetz", "89 New House Rd", "N5X4N8", "5198527824", southernOntario);

            southernOntario.add(mikeKnarr);
            southernOntario.add(paigeWebber);
            southernOntario.add(jeffOregan);
            southernOntario.add(andresHahn);
            southernOntario.add(benProkopetz);


            divisionRepository.save(southernOntario);


        }



    }




}
