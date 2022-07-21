package taco.kitchen.jms;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import taco.kitchen.Order;

@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {

	private JmsTemplate jms;

	public JmsOrderReceiver(JmsTemplate jms) {
		this.jms = jms;
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Order receiveOrder() {
		taco.Order orderTemp = (taco.Order) jms.receiveAndConvert("tacocloud.order.queue");
		return orderTemp == null ? null : modelMapper.map(orderTemp, Order.class);
	}

}
