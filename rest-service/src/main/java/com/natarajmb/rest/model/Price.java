package com.natarajmb.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Holds product price in multiple currency
 */
@Data
@Builder(toBuilder = true)
public class Price {

    @JsonProperty("id")
    private String id;
    @JsonProperty("priceRows")
    private List<PriceRow> priceRows;

}
