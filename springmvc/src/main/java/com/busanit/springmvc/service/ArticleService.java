package com.busanit.springmvc.service;

import com.busanit.springmvc.dto.ArticleForm;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service        // 서비스 컴포넌트 선언
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired    // 서비스 객체 리파지토리 의존성 주입
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> entity
        Article article = dto.toEntity();
        //log.info(article.toString());
        // 2. 타겟 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //log.info(target.toString());
        // 3. 잘못된 요청 처리
        if (target == null || id != target.getId()) {
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    public Article delete(Long id) {
        // 1. 삭제 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2, 잘못된 요청인 경우
        if (target != null) return null;
        // 3. 삭제
        articleRepository.delete(target);
        // 지워진 대상을 컨트롤러에 반환
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 목록을 엔티티 목록으로 전환하기
        List<Article> articleList = dtos.stream()
                                    .map(dto -> dto.toEntity())
                                    .toList();
        //  for 문을 사용할 경우
        /*
        List<Article> articleList1 = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++){
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList1.add(entity);
        }
        */
        // 엔티티 목록을 DB에 저장하기
        articleList.forEach(articleRepository::save);
        
        // 강제 오류 생성
        articleRepository.findById(-1L).orElseThrow(() -> new RuntimeException("결제 실패"));
        return articleList;
    }
}
