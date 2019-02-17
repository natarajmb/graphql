package com.natarajmb.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 *  Holds product price for a currency
 */
@Data
@Builder
public class PriceRow {

    @JsonProperty("price")
    private Double price;
    @JsonProperty("currency")
    private String currency;

}
