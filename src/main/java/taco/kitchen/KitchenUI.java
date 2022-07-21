package taco.kitchen;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import taco.Order;

@Component
@Slf4j
public class KitchenUI {

	public void displayOrder(Order order) {
		// TODO: Beef this up to do more than just log the received taco.
		// To display it in some sort of UI.
		log.info("RECEIVED ORDER:  " + order);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
