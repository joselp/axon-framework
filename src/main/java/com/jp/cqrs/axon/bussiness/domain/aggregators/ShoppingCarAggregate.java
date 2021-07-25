package com.jp.cqrs.axon.bussiness.domain.aggregators;

import com.jp.cqrs.axon.bussiness.usecases.commands.AddProductCommand;
import com.jp.cqrs.axon.bussiness.usecases.commands.CreateShoppingCarCommand;
import com.jp.cqrs.axon.bussiness.usecases.events.ProductAddedEvent;
import com.jp.cqrs.axon.bussiness.usecases.events.ShoppingCarCreatedEvent;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Getter
public class ShoppingCarAggregate {

  @AggregateIdentifier
  private String shoppingCarId;
  private List<String> products;

  //Commands handler
  @CommandHandler
  public ShoppingCarAggregate(CreateShoppingCarCommand cmd) {
    Assert.notNull(cmd.getShoppingCarId(), () -> "shoppingCarId should not be null");

    AggregateLifecycle.apply(new ShoppingCarCreatedEvent(cmd.getShoppingCarId()));
  }

  @CommandHandler
  public void handle(AddProductCommand cmd) {
    Assert.notNull(cmd.getShoppingCarId(), () -> "shoppingCarId should not be null");
    Assert.notNull(cmd.getProductId(), () -> "productId should not be null");

    AggregateLifecycle.apply(new ProductAddedEvent(cmd.getShoppingCarId(), cmd.getProductId()));
  }

  //Events handler
  @EventSourcingHandler
  private void on(ShoppingCarCreatedEvent event) {
    shoppingCarId = event.getShoppingCarId();
    products = new ArrayList<>();
  }

  @EventSourcingHandler
  private void on(ProductAddedEvent event) {
    products.add(event.getProductId());
  }

}