package com.natarajmb.graphql.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.natarajmb.graphql.domain.input.PriceInput;
import com.natarajmb.graphql.domain.input.ProductInput;
import com.natarajmb.graphql.domain.input.StockInput;
import com.natarajmb.graphql.domain.output.Price;
import com.natarajmb.graphql.domain.output.Product;
import com.natarajmb.graphql.domain.output.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * GraphQL schema main mutations implementations
 */
@Component
public class Mutations implements GraphQLMutationResolver {

    @Value("${SERVICE_HOST:localhost}")
    private String hostName;

    private static final String PRODUCTS_ENDPOINT = "http://%s:8080/products";
    private static final String PRICE_ENDPOINT = "http://%s:8080/prices";
    private static final String STOCK_ENDPOINT = "http://%s:8080/stocks";

    private RestTemplate restTemplate;

    @Autowired
    public Mutations(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product updateProduct(ProductInput productInput) {

        Product product = restTemplate.patchForObject(String.format(PRODUCTS_ENDPOINT, hostName),
                productInput, Product.class);

        if (productInput.getPrice() != null && product != null) {
            product.setPrice(setProductPrice(productInput.getPrice()));
        }

        if (productInput.getStock() != null && product != null) {
            product.setStock(setProductStock(productInput.getStock()));
        }
        return product;

    }

    public Price setProductPrice(PriceInput priceInput) {

        return restTemplate.patchForObject(String.format(PRICE_ENDPOINT, hostName), priceInput, Price.class);
        
    }

    public Integer setProductStock(StockInput stockInput) {

        Stock stock = restTemplate.patchForObject(String.format(STOCK_ENDPOINT, hostName), stockInput, Stock.class);
        return stock != null ? stock.getTotalStock() : -1;

    }

}
