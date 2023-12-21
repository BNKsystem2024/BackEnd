package com.bnksys.onemind.apis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Keyword")
@NoArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "word", nullable = false, length = 200)
    private String word;

    @Column(name = "definition", nullable = false, length = 2000)
    private String definition;


    protected Keyword(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public static Keyword of(String word) {
        return new Keyword(word, "NOT DEFINED");
    }

    public static Keyword of(String word, String definition) {
        return new Keyword(word, definition);
    }
}
