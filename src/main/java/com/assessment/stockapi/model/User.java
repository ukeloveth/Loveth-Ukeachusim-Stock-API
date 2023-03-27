package com.assessment.stockapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User extends BaseClass implements Serializable {
    @NotNull
    private String name;
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    private boolean active;
}
