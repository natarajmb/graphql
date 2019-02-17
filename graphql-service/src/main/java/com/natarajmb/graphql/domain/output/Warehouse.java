package com.natarajmb.graphql.domain.output;

import lombok.Data;

/**
 * GraphQL schema representation for the warehouse data
 */
@Data
public class Warehouse {

    private String name;
    private Integer stockOnHand;

}
