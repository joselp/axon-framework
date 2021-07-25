package com.jp.cqrs.axon.bussiness.usecases.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductCommand {

  @TargetAggregateIdentifier
  private String shoppingCarId;
  private String productId;
}
