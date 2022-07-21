package taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import taco.Ingredient;

@CrossOrigin(origins = "*")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
