package com.jp.cqrs.axon.infrastructure.repository;

import com.jp.cqrs.axon.infrastructure.entity.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

  List<Product> findByShoppingCarId(String shoppingCarId);
}