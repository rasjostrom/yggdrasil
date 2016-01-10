package se.lnu.controller;

import javax.inject.Inject;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import se.lnu.domain.Feature;
import se.lnu.domain.Issue;
import se.lnu.domain.Project;
import se.lnu.exception.ResourceNotFoundException;
import se.lnu.repository.FeatureRepository;
import se.lnu.repository.IssueRepository;
import se.lnu.repository.ProjectRepository;


/**
 * Created by nils on 09/01/16.
 */
@RestController
public class ProjectController {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    FeatureRepository featureRepository;

    @Inject
    IssueRepository issueRepository;

    // Get one
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.GET)
    public ResponseEntity<?> getProject(@PathVariable Long projectId) {
        verifyProject(projectId);
        Project p = projectRepository.findOne(projectId);
        return new ResponseEntity<> (p, HttpStatus.OK);
    }


    // Get all
    @RequestMapping(value="/projects", method= RequestMethod.GET)
    public ResponseEntity<Iterable<Project>> getAllProjects() {
        Iterable<Project> allProjects = projectRepository.findAll();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }


    // Create project
    @RequestMapping(value="/projects", method=RequestMethod.POST)
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        project = projectRepository.save(project);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newProjectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(project.getId()).toUri();
        responseHeaders.setLocation(newProjectUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    // TODO: UPDATE NOT WORKING
    // Update project
    // This is the most ugly function that i have ever written
    // I deserve to burn in hell for this
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable Long projectId) {
        // Verify that it exists
        verifyProject(projectId);
        projectRepository.delete(projectId);
        project.setId(projectId);
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Delete project
    @RequestMapping(value="/projects/{projectId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        projectRepository.delete(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Verify that project exists
    protected void verifyProject(Long projectId) throws ResourceNotFoundException {
        Project p = projectRepository.findOne(projectId);
        if(p == null) {
            throw new ResourceNotFoundException("Project with id " + projectId + " not found");
        }
    }

}
