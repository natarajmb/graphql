package com.natarajmb.graphql.domain.output;

import lombok.Data;

import java.util.List;

/**
 * GraphQL schema representation for the stock data
 */
@Data
public class Stock {

    private String id;
    private List<Warehouse> warehouse;
    private Integer totalStock;

}
