package se.lnu.repository;

import org.springframework.data.repository.CrudRepository;
import se.lnu.domain.Issue;

/**
 * Created by nils on 09/01/16.
 */
public interface IssueRepository extends CrudRepository<Issue, Long> {
}
