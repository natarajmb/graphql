package com.natarajmb.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Product stock information in particular warehouse
 */
@Data
@Builder
public class Warehouse {

    @JsonProperty("name")
    private String name;
    @JsonProperty("stockOnHand")
    private Integer stockOnHand;

}
