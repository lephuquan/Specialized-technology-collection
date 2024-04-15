package com.lpq.SpringSecurityJWT.repository;

import com.lpq.SpringSecurityJWT.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {


}
