package se.lnu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.lnu.domain.Project;
import se.lnu.domain.User;
import se.lnu.repository.IssueRepository;
import se.lnu.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by nils on 11/01/16.
 */
@RestController
public class UserController {

    @Inject
    UserRepository userRepository;

    // Get all
    @RequestMapping(value="/users", method= RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getAllProjects() {
        Iterable<User> allUsers = userRepository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
