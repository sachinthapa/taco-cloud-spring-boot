package taco.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import taco.Ingredient;
import taco.Taco;

@Component
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

	public TacoResourceAssembler() {
		super(DesignTacoController.class, TacoResource.class);
	}

	@Override
	public TacoResource toModel(Taco entity) {
		TacoResource tacoModel = instantiateModel(entity);
		tacoModel.setId(entity.getId());
		tacoModel.setName(entity.getName());
		tacoModel.setCreatedAt(entity.getCreatedAt());
		tacoModel.setIngredients(toIngredients(entity.getIngredients()));
		tacoModel.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DesignTacoController.class).tacoById(entity.getId())).withSelfRel());
		return tacoModel;
	}

	@Override
	public CollectionModel<TacoResource> toCollectionModel(Iterable<? extends Taco> entities) {
		CollectionModel<TacoResource> tacoResources = super.toCollectionModel(entities);
		tacoResources.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
		return tacoResources;
	}

	private List<IngredientResource> toIngredients(List<Ingredient> ingredients) {
		if (ingredients.isEmpty())
			return Collections.emptyList();
		return ingredients.stream()
				.map(ingredient -> IngredientResource.builder().id(ingredient.getId()).name(ingredient.getName())
						.build()
						.add(WebMvcLinkBuilder
								.linkTo(WebMvcLinkBuilder.methodOn(IngredientController.class).byId(ingredient.getId()))
								.withSelfRel()))
				.collect(Collectors.toList());
	}

}
