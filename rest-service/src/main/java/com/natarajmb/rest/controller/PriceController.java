package com.natarajmb.rest.controller;

import com.natarajmb.rest.model.Price;
import com.natarajmb.rest.model.PriceRow;
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
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * REST controller for the product pricing
 * <p>
 * Initial list of product are setup up as part of the class initialisation.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {


    private List<Price> priceList = null;

    @PostConstruct
    private void init() {

        priceList = new ArrayList<>();
        priceList.add(Price.builder().id("BT056446")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(45.00).currency("GBP").build(),
                        PriceRow.builder().price(47.20).currency("EUR").build()))
                .build());
        priceList.add(Price.builder().id("BT079418")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(20.25).currency("GBP").build(),
                        PriceRow.builder().price(22.25).currency("EUR").build()))
                .build());
        priceList.add(Price.builder().id("BT084116")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(14.99).currency("GBP").build(),
                        PriceRow.builder().price(16.40).currency("EUR").build()))
                .build());
        priceList.add(Price.builder().id("BT099632")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(73.50).currency("GBP").build(),
                        PriceRow.builder().price(77.20).currency("EUR").build()))
                .build());
        priceList.add(Price.builder().id("BT103983")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(122.40).currency("GBP").build(),
                        PriceRow.builder().price(125.30).currency("EUR").build()))
                .build());
        priceList.add(Price.builder().id("BT129680")
                .priceRows(Arrays.asList(
                        PriceRow.builder().price(8.30).currency("GBP").build(),
                        PriceRow.builder().price(10.10).currency("EUR").build()))
                .build());

    }

    @GetMapping("/prices/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Price getPriceForProduct(@PathVariable String productId) {

        return priceList.stream().filter(price -> price.getId().equals(productId))
                .findFirst().orElseThrow(NoSuchElementException::new);

    }

    @PatchMapping("/prices")
    @ResponseStatus(HttpStatus.OK)
    public void patchPriceForProduct(@RequestBody Price patchPrice) {

        priceList = priceList.stream().map(price -> {
            if (price.getId().equals(patchPrice.getId())) {
                Price newPrice = price.toBuilder().build();
                newPrice.setPriceRows(patchPrice.getPriceRows());
                return newPrice;
            }
            return price;
        }).collect(Collectors.toList());

    }

}
