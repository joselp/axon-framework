package com.jp.cqrs.axon.infrastructure.adapter;

import com.jp.cqrs.axon.bussiness.domain.aggregators.ShoppingCarAggregate;
import com.jp.cqrs.axon.bussiness.usecases.queries.GetShoppingCarQuery;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCarRepositoryAdapter {

  private Repository<ShoppingCarAggregate> repository;

  public ShoppingCarRepositoryAdapter(
      Repository<ShoppingCarAggregate> repository) {
    this.repository = repository;
  }

  @QueryHandler
  public ShoppingCarAggregate getShoppingCar(GetShoppingCarQuery query)
      throws InterruptedException, ExecutionException {
    CompletableFuture<ShoppingCarAggregate> future = new CompletableFuture<ShoppingCarAggregate>();
    repository.load(query.getShoppingCarId()).execute(future::complete);
    return future.get();
  }
}
