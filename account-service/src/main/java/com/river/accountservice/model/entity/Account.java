package com.river.accountservice.model.entity;

import com.river.core.model.persistence.BaseEntity;
import com.river.core.model.vo.Role;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Setter
//    @Column(unique = true, nullable = false)
    private String cartCode;

    @Setter
//    @Column(unique = true, nullable = false)
    private String depositCode;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles.add(Role.BUYER);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
