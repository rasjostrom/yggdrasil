package se.lnu.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nils on 09/01/16.
 */
@Entity
@Table(name="FEATURE")
public class Feature {

    @Id
    @GeneratedValue
    @Column(name="FEATURE_ID")
    private Long id;

    @Column(name="DESCRIPTION")
    private String description;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="FEATURE_ID")
    @OrderBy
    private Set<Issue> issues;

    public Feature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
}
