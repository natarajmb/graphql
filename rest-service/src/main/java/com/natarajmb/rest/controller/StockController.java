package com.natarajmb.rest.controller;

import com.natarajmb.rest.model.Stock;
import com.natarajmb.rest.model.Warehouse;
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
 * REST controller for the stocks
 * <p>
 * Initial list of product stock are setup up as part of the class initialisation.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    private List<Stock> stockList = null;

    @PostConstruct
    private void init() {

        stockList = new ArrayList<>();

        stockList.add(Stock.builder()
                .id("BT056446")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(20).build(),
                        Warehouse.builder().name("South").stockOnHand(10).build()))
                .build());
        stockList.add(Stock.builder()
                .id("BT079418")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(5).build(),
                        Warehouse.builder().name("South").stockOnHand(3).build()))
                .build());
        stockList.add(Stock.builder()
                .id("BT084116")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(14).build(),
                        Warehouse.builder().name("South").stockOnHand(22).build()))
                .build());
        stockList.add(Stock.builder()
                .id("BT099632")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(44).build(),
                        Warehouse.builder().name("South").stockOnHand(3).build()))
                .build());
        stockList.add(Stock.builder()
                .id("BT103983")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(1).build(),
                        Warehouse.builder().name("South").stockOnHand(2).build()))
                .build());
        stockList.add(Stock.builder()
                .id("BT129680")
                .warehouses(Arrays.asList(
                        Warehouse.builder().name("North").stockOnHand(18).build(),
                        Warehouse.builder().name("South").stockOnHand(60).build()))
                .build());

    }

    @GetMapping("/stocks/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Stock getStockForProduct(@PathVariable String productId) {

        return stockList.stream().filter(stock -> stock.getId().equals(productId)).map(stock -> {
            Stock updateStock = stock.toBuilder().build();
            updateStock.setTotalStock(stock.getWarehouses().stream().mapToInt(Warehouse::getStockOnHand).sum());
            return updateStock;
        }).findFirst().orElseThrow(NoSuchElementException::new);

    }

    @PatchMapping("/stocks")
    @ResponseStatus(HttpStatus.OK)
    public Stock patchStock(@RequestBody Stock patchStock) {

        stockList = stockList.stream()
                .map(stock -> {
                    if (stock.getId().equals(patchStock.getId())) {
                        Stock newStock = stock.toBuilder().build();
                        newStock.setWarehouses(patchStock.getWarehouses());
                        return newStock;
                    }
                    return stock;
                }).collect(Collectors.toList());
        return getStockForProduct(patchStock.getId());

    }

}
