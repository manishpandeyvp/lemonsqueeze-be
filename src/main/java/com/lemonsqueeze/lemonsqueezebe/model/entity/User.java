package com.lemonsqueeze.lemonsqueezebe.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank(message = "username cannot be blank")
    @NonNull
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Email Id cannot be null")
    @NonNull
    @Column(name = "email_id", nullable = false)
    private String emailId;

    @NotBlank(message = "password cannot be null")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    @JsonIgnore
    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
    private List<Following> followings;

    static final String TABLE_NAME = "users";
}
