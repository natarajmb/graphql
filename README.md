# GraphQL Java Demo Application #
This is a sample application to demonstrate the following key features
- Queries 
- Mutations 

The project is composed of two sub-projects
- graphql-service: GraphQL service with GraphiQL for interacting with GraphQL 
- rest-service: Backend service provider

## How to run ##

#### Prerequisites ####
* JDK 8 or above
* Ensure port 2020 and 8080 are free to use
* Docker with Docker Compose for your OS

#### Option 1 : Start services on local ####
Execute following commands to start the apps from root folder (order isn't important)

> `./gradlew :graphql-service:clean :graphql-service:build :graphql-service:bootRun`

> `./gradlew :rest-service:clean :rest-service:build :rest-service:bootRun`

 
#### Option 2 : Docker and Docker Compose ####

Execute the following command

> `./gradlew clean build composeUp`

If the above command executes successfully then you should see two containers running.


## Validate and explore ##

If you managed to run the application following either of the approaches mentioned above 
you can now access the GraphQL service using GraphiQL interface at the following address

`http://localhost:2020/graphiql` 

#### Sample queries and mutations ####

- Query for all products 

```javascript
 {
  products{
    id,
    name,
    prices {
      priceRows {
        price,
        currency
      }
    }
    stock
  }
}
```

- Query for a product 

```javascript
query($productId : String)
{
  product(productId : $productId) {
    name,
    id,
    status,
    prices {
      priceRows {
        price,
        currency
      }
    },
    stock
  }
}

// variable definition, copy to Query Variables in GraphiQL
{
    "productId": "BT056446"
}
```

- Mutation operation

```javascript
mutation product($productInput: ProductInput) {
  updateProduct(productInput: $productInput) {
    id,
    name,
    status
    stock,
    prices {
      id,
      priceRows {
        price,
        currency
      }
    }
  }
}

// variable definition, copy to Query Variables in GraphiQL
{
  "productInput": {
    "id": "BT056446",
    "name": "12 pce White Scratches Dinner Set",
    "status": "online",
    "price": {
      "id": "BT056446",
      "priceRows": [
        {
          "currency": "GBP",
          "price": 49.5
        },
        {
          "currency": "EUR",
          "price": 55.6
        }
      ]
    },
    "stock": {
      "id": "BT056446",
      "warehouses": [
        {
          "name": "north",
          "stockOnHand": 14
        },
        {
          "name": "north",
          "stockOnHand": 24
        }
      ]
    }
  }
}
```