package taco.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import taco.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
