package com.busanit.springmvc.repository;

import com.busanit.springmvc.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // 기본 Iterable -> Arraylist 로 타입 오버라이딩
    @Override
    ArrayList<Article> findAll();

}
