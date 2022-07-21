package taco.kitchen.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import taco.Order;
import taco.kitchen.KitchenUI;

@Profile("jms-listener")
@Component
public class OrderListener {

	private KitchenUI ui;

	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}

//	@JmsListener(destination = "tacocloud.order.queue", containerFactory = "myFactory")
	public void receiveOrder(Order order) {
		ui.displayOrder(order);
	}

}
