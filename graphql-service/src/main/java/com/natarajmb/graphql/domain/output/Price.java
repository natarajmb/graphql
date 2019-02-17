package com.natarajmb.graphql.domain.output;

import lombok.Data;

import java.util.List;

/**
 * GraphQL schema representation for the price data
 */
@Data
public class Price {

    private String id;
    private List<PriceRow> priceRows;

}
