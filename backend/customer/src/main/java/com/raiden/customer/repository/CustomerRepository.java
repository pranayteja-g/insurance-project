package com.raiden.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raiden.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
