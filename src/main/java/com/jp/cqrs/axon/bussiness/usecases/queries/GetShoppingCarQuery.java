package com.jp.cqrs.axon.bussiness.usecases.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetShoppingCarQuery {

  private String shoppingCarId;

}
