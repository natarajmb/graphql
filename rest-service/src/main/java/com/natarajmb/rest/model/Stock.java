package com.natarajmb.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Keeps stock information about a product across multiple warehouses
 */
@Data
@Builder(toBuilder = true)
public class Stock {

    @JsonProperty("id")
    private String id;
    @JsonProperty("warehouseRespons")
    private List<Warehouse> warehouses;
    @JsonProperty("totalStock")
    private Integer totalStock;

}
