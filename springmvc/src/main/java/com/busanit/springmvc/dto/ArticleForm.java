package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO : Data Transfer Object
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;

// 요청받은 제목과 내용을 필드에 저장하는 생성자
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }


    // DTO를 Entity로 변환
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
