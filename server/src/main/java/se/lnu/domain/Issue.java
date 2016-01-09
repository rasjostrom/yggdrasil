package se.lnu.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by nils on 09/01/16.
 */

@Entity
@Table(name="ISSUE")
public class Issue {

    @Id
    @GeneratedValue
    @Column(name="ISSUE_ID")
    private Long id;

    @Type(type="yes_no")
    private boolean status;

    @Column(name="DECRIPTION")
    private String description;

    public Issue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
