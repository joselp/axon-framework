package com.jp.cqrs.axon.application;

import com.jp.cqrs.axon.bussiness.domain.aggregators.ShoppingCarAggregate;
import com.jp.cqrs.axon.bussiness.usecases.queries.GetProductsQuery;
import com.jp.cqrs.axon.bussiness.usecases.queries.GetShoppingCarQuery;
import com.jp.cqrs.axon.infrastructure.entity.Product;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCarQueryController {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private QueryGateway queryGateway;

  @Autowired
  public ShoppingCarQueryController(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  @GetMapping("/shopping-car/{shoppingCarId}")
  public ResponseEntity<ShoppingCarAggregate> getShoppingCar(@PathVariable String shoppingCarId)
      throws ExecutionException, InterruptedException {

    logger.info("Getting shopping car");

    CompletableFuture<ShoppingCarAggregate> future = queryGateway
        .query(new GetShoppingCarQuery(shoppingCarId), ShoppingCarAggregate.class);

    return ResponseEntity.ok(future.get());
  }

  @GetMapping("/shopping-car/{shoppingCarId}/products")
  public ResponseEntity<List<Product>> getProducts(@PathVariable String shoppingCarId)
      throws ExecutionException, InterruptedException {

    logger.info("Getting products by shopping car");

    CompletableFuture<List<Product>> future = queryGateway
        .query(new GetProductsQuery(shoppingCarId),
            ResponseTypes.multipleInstancesOf(Product.class));

    return ResponseEntity.ok(future.get());
  }
}