package se.lnu.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nils on 09/01/16.
 */

@Entity
@Table(name="PROJECT")
public class Project {

    @Id
    @GeneratedValue
    @Column(name="PROJECT_ID")
    private Long id;

    @Column(name="TITLE")
    @NotEmpty
    private String title;

    @Column(name="DESCRIPTION")
    @NotEmpty
    private String description;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PROJECT_ID")
    @OrderBy
    private Set<Feature> features;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PROJECT_ID")
    @OrderBy
    private Set<Comment> comments;

    // Empty constructor
    // For JPA
    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
