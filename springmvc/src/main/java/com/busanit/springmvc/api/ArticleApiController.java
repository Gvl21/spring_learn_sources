package com.busanit.springmvc.api;

import com.busanit.springmvc.dto.ArticleForm;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.repository.ArticleRepository;
import com.busanit.springmvc.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    // DI
    private final ArticleService articleService;
    // private final ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        // 서비스 계층에 위임
         return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        // 서비스 계층에서 데이터를 생성
        Article created = articleService.create(dto);
        // 삼항연산자 (조건) ? 성공 : 실패
        // 리턴된 생성 엔티티가 있을경우 200번 반환 : 없을 경우 400번 반환
        return (created != null)
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 서비스 계층에 위임
        Article updated = articleService.update(id, dto);
        // 응답
        return (updated != null)
                ? ResponseEntity.status(HttpStatus.OK).body(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null )
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    // 여러개의 게시글을 한꺼번에 생성하는 요청
    @PostMapping("api/articles/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        // 서비스 계층에 위임 => 결과 반환
        List<Article>createdList = articleService.createArticles(dtos);
        // 응답 객체 리턴(성공, 실패)
        return (createdList != null)
                ? ResponseEntity.status(HttpStatus.OK).body(createdList)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }




}
