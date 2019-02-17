package com.natarajmb.graphql.domain.input;

import lombok.Data;

/**
 * GraphQL schema input representation for the warehouse data
 */
@Data
public class WarehouseInput {

    private String name;
    private Integer stockOnHand;

}
