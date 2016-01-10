package se.lnu.repository;

import org.springframework.data.repository.CrudRepository;
import se.lnu.domain.User;

/**
 * Created by nils on 10/01/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
