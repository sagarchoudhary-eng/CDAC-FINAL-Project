package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Address;

public interface IAddressRepository extends JpaRepository<Address, Integer> {

}
