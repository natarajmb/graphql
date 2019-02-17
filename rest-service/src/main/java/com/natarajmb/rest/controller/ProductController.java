package com.natarajmb.rest.controller;


import com.natarajmb.rest.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * REST controller for the product data
 * <p>
 * Initial list of product are setup up as part of the class initialisation.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private List<Product> productList = null;

    @PostConstruct
    private void init() {
        
        productList = new ArrayList<>();
        productList.add(Product.builder()
                .id("BT056446")
                .name("16 pce White Scratches Dinner Set")
                .status("online")
                .build());
        productList.add(Product.builder()
                .id("BT079418")
                .name("Set of 4 Leonardo Traditional Floral Mugs with Trailing Ivy Design.")
                .status("online")
                .build());
        productList.add(Product.builder()
                .id("BT084116")
                .name("Set of 6 Carson spring mugs")
                .status("online")
                .build());
        productList.add(Product.builder()
                .id("BT099632")
                .name("Viners 58 Piece King's Stainless Steel Cutlery Set with Canteen Case")
                .status("offline")
                .build());
        productList.add(Product.builder()
                .id("BT103983")
                .name("Anthony Joseph 12 Piece Stoneware Dinner Set")
                .status("online")
                .build());
        productList.add(Product.builder()
                .id("BT129680")
                .name("Mike Payne Frank Sinatra Set of 4 Black Latte Mugs with Graphic Style " +
                        "Picture of the Legend")
                .status("online")
                .build());

    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {

        return productList;

    }

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String productId) {

        return productList.stream().filter(product -> product.getId().equals(productId))
                .findFirst().orElseThrow(NoSuchElementException::new);

    }

    @PatchMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Product patchProduct(@RequestBody Product patchProduct) {

        productList = productList.stream()
                .map(product -> {
                    if (product.getId().equals(patchProduct.getId())) {
                        Product newProduct = product.toBuilder().build();
                        newProduct.setName(patchProduct.getName());
                        newProduct.setStatus(patchProduct.getStatus());
                        return newProduct;
                    }
                    return product;
                }).collect(Collectors.toList());
        return getProduct(patchProduct.getId());

    }

}
