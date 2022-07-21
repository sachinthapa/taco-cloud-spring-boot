package taco.messaging;

import taco.kitchen.Order;

public interface OrderReceiver {

	Order receiveOrder();

}