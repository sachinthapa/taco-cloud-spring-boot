package taco.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import taco.Order;
import taco.data.OrderRepository;
import taco.messaging.OrderMessagingService;

@RepositoryRestController
@CrossOrigin(origins = "*")
public class OrderApiController {

	@Autowired
	private OrderRepository repo;

	@Autowired
	@Qualifier("KafkaOrderMessagingService")
	private OrderMessagingService orderMessagingService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/hal+json")
	public ResponseEntity<Iterable<Order>> allOrders() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = "application/hal+json")
	public ResponseEntity<Order> postOrder(@RequestBody Order order) {
		System.out.println("OrderApiController postOrder");
		orderMessagingService.sendOrder(order);
		Order orderSaved = repo.save(order);
		return new ResponseEntity<>(orderSaved, HttpStatus.CREATED);
	}

	@PutMapping(path = "/orders/{orderId}", consumes = "application/json")
	public Order putOrder(@RequestBody Order order) {
		return repo.save(order);
	}

	@PatchMapping(path = "/orders/{orderId}", produces = "application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {

		Order order = repo.findById(orderId).get();
		if (patch.getDeliveryName() != null) {
			order.setDeliveryName(patch.getDeliveryName());
		}
		if (patch.getDeliveryStreet() != null) {
			order.setDeliveryStreet(patch.getDeliveryStreet());
		}
		if (patch.getDeliveryCity() != null) {
			order.setDeliveryCity(patch.getDeliveryCity());
		}
		if (patch.getDeliveryState() != null) {
			order.setDeliveryState(patch.getDeliveryState());
		}
		if (patch.getDeliveryZip() != null) {
			order.setDeliveryZip(patch.getDeliveryState());
		}
		if (patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if (patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if (patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		return repo.save(order);
	}

	@DeleteMapping("/order/{orderId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			repo.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {
		}
	}

}
