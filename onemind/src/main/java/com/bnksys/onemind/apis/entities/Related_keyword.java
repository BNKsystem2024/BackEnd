package com.bnksys.onemind.apis.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Related_keyword")
@NoArgsConstructor
public class Related_keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    protected Related_keyword(Keyword keyword, Quiz quiz) {
        this.keyword = keyword;
        this.quiz = quiz;
    }

    public static Related_keyword of(Keyword keyword, Quiz quiz) {
        return new Related_keyword(keyword, quiz);
    }

}
