package taco.messaging.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "rabbit-template", "rabbit-listener" })
public class MessagingConfig {

	@Bean
	public Queue queue() {
		return new Queue("tacocloud.order.queue");
	}
//
//	@Bean
//	public TopicExchange exchange() {
//		return new TopicExchange("tacocloud.exchange");
//	}
//
//	@Bean
//	public Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with("tacocloud.#");
//	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
