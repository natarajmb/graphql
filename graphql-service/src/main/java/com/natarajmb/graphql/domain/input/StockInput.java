package com.natarajmb.graphql.domain.input;

import lombok.Data;

import java.util.List;

/**
 * GraphQL schema input representation for the stock data
 */
@Data
public class StockInput {

    private String id;
    private List<WarehouseInput> warehouses;

}
