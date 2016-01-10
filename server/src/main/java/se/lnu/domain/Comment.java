package se.lnu.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by nils on 11/01/16.
 */
@Entity
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

    @Column(name="TEXT")
    private String text;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
