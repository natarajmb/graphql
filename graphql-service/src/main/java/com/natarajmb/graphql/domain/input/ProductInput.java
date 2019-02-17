package com.natarajmb.graphql.domain.input;

import lombok.Data;

/**
 * GraphQL schema input representation for the product data
 */
@Data
public class ProductInput {

    private String id;
    private String name;
    private String status;
    private PriceInput price;
    private StockInput stock;

}
