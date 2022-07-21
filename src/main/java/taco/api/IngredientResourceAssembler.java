package taco.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import taco.Ingredient;

@Component
class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

	public IngredientResourceAssembler() {
		super(IngredientController.class, IngredientResource.class);
	}

	@Override
	public IngredientResource toModel(Ingredient entity) {
		IngredientResource ingredientResource = instantiateModel(entity);
		ingredientResource.setId(entity.getId());
		ingredientResource.setName(entity.getName());
		ingredientResource.setType(entity.getType());
		ingredientResource.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(IngredientController.class).byId(entity.getId())).withSelfRel());
		return ingredientResource;
	}

	@Override
	public CollectionModel<IngredientResource> toCollectionModel(Iterable<? extends Ingredient> entities) {
		System.out.println("entities:" + entities);
		CollectionModel<IngredientResource> tacoResources = super.toCollectionModel(entities);
		tacoResources.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(IngredientController.class).allIngredients()).withRel("recents"));
		return tacoResources;
	}

}