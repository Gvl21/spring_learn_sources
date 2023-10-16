package com.busanit.springmvc.service;

// Test 패키지
import com.busanit.springmvc.dto.ArticleForm;
import com.busanit.springmvc.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest     // 해당 테스트 클래스를 스프링 부트와 연동해서 테스트
class ArticleServiceTest {

    @Autowired      // 의존성 필드 주입
    ArticleService articleService;

    @Test
    void index() {
        // given - when - then
        // 1. 예상되는 데이터 => expected
        Article a = new Article(1L, "제목1", "내용1");
        Article b = new Article(2L, "제목2", "내용2");
        Article c = new Article(3L, "제목3", "내용3");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        // 2. 실제 데이터 => actual
        List<Article> articleList = articleService.index();
        // 3. 비교와 검증
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_성공_존재하는ID입력() {
        // 1. expected 예상
        Long id = 1L;
        Article expected = new Article(id, "제목1", "내용1");
        // 2. actual 실제
        Article actual = articleService.show(id);
        // 3. assertion 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_실패_존재하지않는ID입력() {
        // 1. expected 예상
        Long id = -1L;
        Article expected = null;
        // 2. actual 실제
        Article actual = articleService.show(id);
        // 3. assertion 검증
        assertEquals(expected, actual);
    }

    @Test
    @Transactional  // 테스트가 종료한 뒤 변경된 데이터를 rollback
    void create_성공_dto입력() {
        // expected
        String title = "제목4";
        String content = "내용4";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(4L, title, content);
        // actual
        Article actual = articleService.create(dto);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test   //  이 메서드는 테스트 코드다.
    void update_타이틀과_컨텐츠_모두수정하는경우() {
        // expected
        Long id = 1L;
        String title = "수정 타이틀";
        String content = "수정 콘텐츠";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(id, title, content
        );
        // actual
        Article actual = articleService.update(id, dto);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_타이틀만_수정하는경우() {
        // expected
        Long id = 1L;
        String title = "수정 타이틀";
        String content = null;
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(id, title, "내용1"
        );
        // actual
        Article actual = articleService.update(id, dto);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_콘텐츠만_수정하는경우() {
        // expected
        Long id = 1L;
        String title = null;
        String content = "수정 컨텐츠";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(id, "제목1", content
        );
        // actual
        Article actual = articleService.update(id, dto);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지않는ID_수정하는경우() {
        // expected
        Long id = -1L;
        String title = "수정 타이틀";
        String content = "수정 컨텐츠";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = null;
        // actual
        Article actual = articleService.update(id, dto);
        // assert
        assertEquals(expected, actual);
    }
    @Test
    @Transactional
    void delete_성공() {
        // expected
        Long id = 1L;
        Article expected = new Article(1L, "제목1", "내용1");
        // actual
        Article actual = articleService.delete(id);
        // assert
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Transactional
    void delete_실패() {
        // expected
        Long id = -1L;
        Article expected = null;
        // actual
        Article actual = articleService.delete(id);
        // assert
        assertEquals(expected, actual);
    }

}