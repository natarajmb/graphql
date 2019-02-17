package com.natarajmb.graphql.domain.input;

import lombok.Data;

import java.util.List;

/**
 * GraphQL schema input representation for the price data
 */
@Data
public class PriceInput {

    private String id;
    private List<PriceRowInput> priceRows;

}
