package taco.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import taco.Ingredient;
import taco.data.IngredientRepository;

@RepositoryRestController
@CrossOrigin(origins = "*")
public class IngredientController {

	private IngredientRepository repo;

	@Autowired
	private IngredientResourceAssembler ingredientResourceAssembler;

	public IngredientController(IngredientRepository repo) {
		this.repo = repo;
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET, produces = "application/hal+json")
	public ResponseEntity<IngredientResource> byId(@PathVariable String id) {
		Ingredient ingredient = repo.findById(id).get();
		return new ResponseEntity<>(ingredientResourceAssembler.toModel(ingredient), HttpStatus.OK);
	}

	@GetMapping(value = "/ingredient", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<IngredientResource>> allIngredients() {
		Iterable<Ingredient> ingredients = repo.findAll();
		return new ResponseEntity<>(ingredientResourceAssembler.toCollectionModel(ingredients), HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {
		Ingredient saved = repo.save(ingredient);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("http://localhost:8080/api/ingredients" + ingredient.getId()));
		return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "ingredient/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public void deleteIngredient(@PathVariable String id) {
		repo.deleteById(id);
	}

}
