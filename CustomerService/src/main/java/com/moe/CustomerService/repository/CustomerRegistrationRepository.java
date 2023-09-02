package com.moe.CustomerService.repository;

import com.moe.CustomerService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRegistrationRepository extends JpaRepository<Customer, Integer> {
}
