package com.natarajmb.graphql.domain.output;

import lombok.Data;

/**
 * GraphQL schema representation for the price row data
 */
@Data
public class PriceRow {

    private Double price;
    private String currency;

}
