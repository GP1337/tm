package com.taskmanager.tm.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;

}
