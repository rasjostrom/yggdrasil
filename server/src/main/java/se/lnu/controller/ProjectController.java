package se.lnu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.lnu.domain.Project;
import se.lnu.repository.ProjectRepository;

import javax.inject.Inject;
import java.net.URI;

/**
 * Created by nils on 09/01/16.
 */
@RestController
public class ProjectController {

    @Inject
    ProjectRepository projectRepository;

    // Get all
    @RequestMapping(value="/projects", method= RequestMethod.GET)
    public ResponseEntity<Iterable<Project>> getAllPolls() {
        Iterable<Project> allProjects = projectRepository.findAll();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    // Create project
    @RequestMapping(value="/projects", method=RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Project project) {
        project = projectRepository.save(project);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(project.getId()).toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
