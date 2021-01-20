package com.taskmanager.tm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@JsonIgnoreProperties(value = { "user", "username" })
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private  boolean completed;

    @Column
    private LocalDateTime creationDateTime;

    @Formula("username")
    private String username;

    public Task() {

    }

    public Task(String title) {

        this.title = title;

        this.creationDateTime = LocalDateTime.now();

    }

}
