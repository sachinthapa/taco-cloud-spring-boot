package taco.data;

import org.springframework.data.repository.CrudRepository;
import taco.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
