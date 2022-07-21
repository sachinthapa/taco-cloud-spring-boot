package taco.messaging;

import taco.Order;

public interface OrderMessagingService {

  void sendOrder(Order order);
  
}
