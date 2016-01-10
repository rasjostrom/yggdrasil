package se.lnu.repository;

import org.springframework.data.repository.CrudRepository;
import se.lnu.domain.Comment;

/**
 * Created by nils on 11/01/16.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
