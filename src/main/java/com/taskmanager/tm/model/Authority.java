package com.taskmanager.tm.model;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@IdClass(AuthorityId.class)
public class Authority implements GrantedAuthority {

    @Id
    private String username;

    @Id
    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
