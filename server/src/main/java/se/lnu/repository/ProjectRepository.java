package se.lnu.repository;

import org.springframework.data.repository.CrudRepository;
import se.lnu.domain.Project;

/**
 * Created by nils on 09/01/16.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
