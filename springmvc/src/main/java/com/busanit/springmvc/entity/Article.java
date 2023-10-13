package com.busanit.springmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity     // Entity 선언
public class Article {
    @Id     // 식별자(PK)
    @GeneratedValue     // 숫자 자동생성
    private Long id;
    @Column     // DB테이블의 열과 연결
    private String title;

    @Column
    private String content;

    public Article() {
    }

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }

    }
}
