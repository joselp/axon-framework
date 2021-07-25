package com.jp.cqrs.axon.application;

import com.jp.cqrs.axon.bussiness.usecases.commands.AddProductCommand;
import com.jp.cqrs.axon.bussiness.usecases.commands.CreateShoppingCarCommand;
import java.util.UUID;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCarCommandController {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  private CommandGateway commandGateway;

  @Autowired
  public ShoppingCarCommandController(
      CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/shopping-car")
  public ResponseEntity<String> createShoppingCar() {

    logger.info("Creating new shopping car");
    String id = UUID.randomUUID().toString();

    commandGateway.send(new CreateShoppingCarCommand(id));

    return ResponseEntity.status(HttpStatus.CREATED).body(id);
  }

  @PostMapping("/shopping-car/{id}/product")
  public ResponseEntity<String> addProduct(@PathVariable("id") String id) {

    logger.info("Adding Product");
    String productId = UUID.randomUUID().toString();

    commandGateway.send(new AddProductCommand(id, productId));

    return ResponseEntity.ok(productId);
  }
}