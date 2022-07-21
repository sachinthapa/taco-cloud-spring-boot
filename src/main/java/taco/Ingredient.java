package taco;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestResource(rel = "ingredients", path = "ingredient")
public class Ingredient {

	@Id
	private String id;
	private String name;
	private Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}
