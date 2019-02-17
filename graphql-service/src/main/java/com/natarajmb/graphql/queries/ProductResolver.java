package com.natarajmb.graphql.queries;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.natarajmb.graphql.domain.output.Price;
import com.natarajmb.graphql.domain.output.Product;
import com.natarajmb.graphql.domain.output.Stock;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Product resolver to resolve rest of the attributes on the product mode.
 * <p>
 * NOTE: Always call the resolver methods with {@link Product} as the parameter optionally
 * can include {@link DataFetchingEnvironment} as the last parameter to resolver methods.
 */
@Component
public class ProductResolver implements GraphQLResolver<Product> {

    @Value("${SERVICE_HOST:localhost}")
    private String hostName;

    private static final String PRICE_ENDPOINT = "http://%s:8080/prices/%s";
    private static final String STOCK_ENDPOINT = "http://%s:8080/stocks/%s";

    private RestTemplate restTemplate;

    public ProductResolver(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // example type resolver
    public Price getPrices(Product product, DataFetchingEnvironment dataFetchingEnvironment) {

        ResponseEntity<Price> priceResponseEntity = restTemplate.exchange(
                String.format(PRICE_ENDPOINT, hostName, product.getId()), HttpMethod.GET, null, Price.class);
        return priceResponseEntity.getBody();

    }

    // example attribute resolver
    public int stock(Product product, DataFetchingEnvironment dataFetchingEnvironment) {

        ResponseEntity<Stock> priceResponseEntity = restTemplate.exchange(
                String.format(STOCK_ENDPOINT, hostName, product.getId()), HttpMethod.GET, null, Stock.class);
        return priceResponseEntity.getBody().getTotalStock();

    }

}
