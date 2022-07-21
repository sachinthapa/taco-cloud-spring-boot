package taco.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import taco.Taco;
import taco.data.TacoRepository;

@RestController("ApiDesignTacoController")
@CrossOrigin(origins = "*")
public class DesignTacoController {

	@Autowired
	private TacoResourceAssembler tacoResourceAssembler;

	@Autowired
	private TacoRepository tacoRepo;

	@RequestMapping(path = "/api/design", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}

	@RequestMapping(value = "/api/design/{id}", method = RequestMethod.GET, produces = "application/hal+json")
	public ResponseEntity<TacoResource> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(tacoResourceAssembler.toModel(optTaco.get()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
