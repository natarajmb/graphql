package com.natarajmb.graphql.domain.input;

import lombok.Data;

/**
 * GraphQL schema input representation for the price row data
 */
@Data
public class PriceRowInput {

    private Double price;
    private String currency;

}
