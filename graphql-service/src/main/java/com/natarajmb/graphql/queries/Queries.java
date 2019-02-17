package com.natarajmb.graphql.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.natarajmb.graphql.domain.output.Product;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Root resolver for the product domain. Once main type is resolved {@link ProductResolver}
 * is called to resolve the rest of fields.
 */
@Component
public class Queries implements GraphQLQueryResolver {

    @Value("${SERVICE_HOST:localhost}")
    private String hostName;

    private static final String PRODUCTS_ENDPOINT = "http://%s:8080/products";
    private static final String PRODUCT_ENDPOINT = "http://%s:8080/products/%s";

    private RestTemplate restTemplate;

    @Autowired
    public Queries(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts(DataFetchingEnvironment dataFetchingEnvironment) {

        ResponseEntity<List<Product>> productResponseEntity = restTemplate.exchange(
                String.format(PRODUCTS_ENDPOINT, hostName), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Product>>() {});
        return productResponseEntity.getBody();

    }

    public Product getProduct(String productId) {

        ResponseEntity<Product> productResponseEntity = restTemplate
                .getForEntity(String.format(PRODUCT_ENDPOINT, hostName, productId), Product.class);
        return productResponseEntity.getBody();

    }

}
