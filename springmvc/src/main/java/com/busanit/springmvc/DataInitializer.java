package com.busanit.springmvc;

import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Coffee;
import com.busanit.springmvc.entity.Comment;
import com.busanit.springmvc.entity.Pizza;
import com.busanit.springmvc.repository.ArticleRepository;
import com.busanit.springmvc.repository.CoffeeRepository;
import com.busanit.springmvc.repository.CommentRepository;
import com.busanit.springmvc.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 스프링부트 앱 초기 구동 시 실행되는 커맨드라인러너
@Component
@RequiredArgsConstructor    // 생성자 의존성 주입
public class DataInitializer implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    private final CoffeeRepository coffeeRepository;
    private final CommentRepository commentRepository;
    private final PizzaRepository pizzaRepository;

    @Override
    public void run(String... args) throws Exception {
        // 게시글 임시 mock data
        articleRepository.save(new Article(1L, "제목1", "내용1"));
        articleRepository.save(new Article(2L, "제목2", "내용2"));
        articleRepository.save(new Article(3L, "제목3", "내용3"));
        // 커피 임시 mock data
        coffeeRepository.save(new Coffee(1L, "아메리카노", "4500"));
        coffeeRepository.save(new Coffee(2L, "라떼", "5000"));
        coffeeRepository.save(new Coffee(3L, "카페모카", "5500"));

        // 댓글 임시 mock data
        Article article4 = new Article(4L, "인생 영화는?", "댓글 달아주세요");
        Article article5 = new Article(5L, "인생 음식은?", "댓글 달아주세요");
        Article article6 = new Article(6L, "취미는?", "댓글 달아주세요");
        articleRepository.save(article4);
        articleRepository.save(article5);
        articleRepository.save(article6);
        // 영화 댓글
        commentRepository.save(new Comment(1L, article4, "john", "기생충"));
        commentRepository.save(new Comment(2L, article4, "jane", "명량"));
        commentRepository.save(new Comment(3L, article4, "tom", "신과함께"));
        // 음식 댓글
        commentRepository.save(new Comment(4L, article5, "tom", "라면"));
        commentRepository.save(new Comment(5L, article5, "john", "치킨"));
        commentRepository.save(new Comment(6L, article5, "jane", "피자"));
        // 취미
        commentRepository.save(new Comment(7L, article6, "jane", "산책"));
        commentRepository.save(new Comment(8L, article6, "john", "독서"));
        commentRepository.save(new Comment(9L, article6, "tom", "영화"));

        pizzaRepository.save(new Pizza(1L, "걍 피자", "천원"));
        pizzaRepository.save(new Pizza(2L, "걍 피자1", "천원1"));
        pizzaRepository.save(new Pizza(3L, "걍 피자2", "천원2"));
    }
}