package com.tutorialspoint.demo.controller;

import com.tutorialspoint.demo.exception.ProductNotfoundException;
import com.tutorialspoint.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceOriginalController {
    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value="/products", method=RequestMethod.GET)
    public ResponseEntity<Object> updateProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value="/products/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        if(!productRepo.containsKey(id))
            throw new ProductNotfoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/products/{id}", method=RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("id") String id) {
        if(!productRepo.containsKey(id))
            throw new ProductNotfoundException();
        return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
    }

    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity<Object> handleException(ProductNotfoundException exception) {
        return new ResponseEntity<>("coollll not found", HttpStatus.NOT_FOUND);
    }
}