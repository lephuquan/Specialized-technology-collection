package com.lpq.SpringSecurityJWT.controller;


import com.lpq.SpringSecurityJWT.dto.ReqRes;
import com.lpq.SpringSecurityJWT.entity.Product;
import com.lpq.SpringSecurityJWT.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepo.findAll());
    }

    @GetMapping("/admin/saveproduct")
    public ResponseEntity<Object> getAllProducts(@RequestBody ReqRes productRequest){
        Product productToSave = new Product();
        productToSave.setName(productRequest.getName());
        return ResponseEntity.ok(productRepo.save(productToSave));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("Users alone access this API only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAminAndUserApi(){
        return ResponseEntity.ok("Both Amin and Users can access this API");
    }
}
