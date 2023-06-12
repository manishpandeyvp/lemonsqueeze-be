package com.lemonsqueeze.lemonsqueezebe.model.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Recipe.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "desc")
    private String desc;

    @NonNull
    @ElementCollection
    @CollectionTable(name = "steps", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "steps") 
    private List<String> steps;

    @NonNull
    @Column(name = "tips")
    private String tips;

    @NonNull
    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "ingredients") 
    private List<String> ingredients;

    @NonNull
    @Column(name = "img_url")
    private String imgUrl;

    @NonNull
    @Column(name = "time_stamp")
    private String timeStamp;

    @NotBlank(message = "Author could not be blank.")
    @Column(name = "author")
    private String author;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "posted_by", referencedColumnName = "username")
    private User postedBy;

    static final String TABLE_NAME = "recipes";
}
