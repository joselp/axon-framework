package com.jp.cqrs.axon.bussiness.usecases.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCarCreatedEvent {

  @TargetAggregateIdentifier
  private String shoppingCarId;

}