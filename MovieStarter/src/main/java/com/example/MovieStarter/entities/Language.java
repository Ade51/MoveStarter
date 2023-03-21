package com.example.MovieStarter.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Languages")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
