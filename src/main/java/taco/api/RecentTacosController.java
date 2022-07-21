package taco.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import taco.Taco;
import taco.data.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

	@Autowired
	private TacoResourceAssembler tacoResourceAssembler;

	private TacoRepository tacoRepo;

	public RecentTacosController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<TacoResource>> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		return new ResponseEntity<>(tacoResourceAssembler.toCollectionModel(tacos), HttpStatus.OK);
	}

}
