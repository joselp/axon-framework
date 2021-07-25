package com.jp.cqrs.axon.infrastructure.adapter;

import com.jp.cqrs.axon.bussiness.usecases.events.ProductAddedEvent;
import com.jp.cqrs.axon.bussiness.usecases.queries.GetProductsQuery;
import com.jp.cqrs.axon.infrastructure.entity.Product;
import com.jp.cqrs.axon.infrastructure.repository.ProductRepository;
import java.util.List;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductRepositoryAdapter {

  private ProductRepository productRepository;

  public ProductRepositoryAdapter(
      ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @EventHandler
  public void addProduct(ProductAddedEvent event) {
    productRepository.save(Product.builder()
        .id(event.getProductId())
        .shoppingCarId(event.getShoppingCarId())
        .build());
  }

  @QueryHandler
  public List<Product> getProducts(GetProductsQuery query) {
    return productRepository.findByShoppingCarId(query.getShoppingCarId());
  }
}
