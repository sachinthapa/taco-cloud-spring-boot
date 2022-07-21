package taco.kitchen.jms;

import taco.kitchen.Order;

public interface OrderReceiver {

	Order receiveOrder();

}