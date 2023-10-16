package com.busanit.springmvc;

import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Coffee;
import com.busanit.springmvc.repository.ArticleRepository;
import com.busanit.springmvc.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 스프링부트 앱 초기 구동시 실행되는 커맨드라인러너
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // 임시 mock data
        articleRepository.save(new Article(1L, "제목1", "내용1"));
        articleRepository.save(new Article(2L, "제목2", "내용2"));
        articleRepository.save(new Article(3L, "제목3", "내용3"));

        coffeeRepository.save(new Coffee(1L, "아메리카노", "4500"));
        coffeeRepository.save(new Coffee(2L, "라떼", "5000"));
        coffeeRepository.save(new Coffee(3L, "카페모카", "5500"));
    }
}
