package taco.kitchen.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import taco.kitchen.Order;

@Profile({ "jms-template", "rabbitmq-template" })
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {

	@Autowired
	private OrderReceiver jmsOrderReceiver;

	@GetMapping("/receive")
	public String receiveOrder(Model model) {
		Order order = jmsOrderReceiver.receiveOrder();
		if (order != null) {
			model.addAttribute("order", order);
			return "receiveOrder";
		}
		return "noOrder";
	}

}
