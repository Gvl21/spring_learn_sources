package com.busanit.springmvc.repository;

import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired      // 의존성 주입
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글을 조회")
    void findByArticleId() {
        // 4번 게시글의 댓글 조회
        {
            // 4번 글 댓글 조회
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article4 = new Article(4L, "인생 영화는?", "댓글 달아주세요");
            Comment comment1 = new Comment(1L, article4, "john", "기생충");
            Comment comment2 = new Comment(2L, article4, "jane", "명량");
            Comment comment3 = new Comment(3L, article4, "tom", "신과함께");
            List<Comment> expected = Arrays.asList(comment1, comment2, comment3);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "4번 게시글 댓글 조회");
        }
        // 테스트 케이스 : 1번 게시글의 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();   // []
            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "1번 게시글 댓글 조회, 텅 비어있어야 함.");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 댓글 조회")
    void findByNickname() {
        // 테스트 케이스 : John의 게시글 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "john";
            // 2. 실제 데이터
            List<Comment> actual = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment comment1 = new Comment(1L, new Article(4L, "인생 영화는?", "댓글 달아주세요"), "john", "기생충");
            Comment comment2 = new Comment(5L, new Article(5L, "인생 음식은?", "댓글 달아주세요"), "john", "치킨");
            Comment comment3 = new Comment(8L, new Article(6L, "취미는?", "댓글 달아주세요"), "john", "독서");
            List<Comment> expected = Arrays.asList(comment1, comment2, comment3);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), actual.toString(), "john의 댓글 출력");
        }
        // 테스트 케이스 : nickname이 null일 경우 댓글 조회
        {
            String nickname = null;
            List<Comment> actual = commentRepository.findByNickname(nickname); // []
            List<Comment> expected = Arrays.asList(); // []
            assertEquals(actual.toString(), expected.toString(), "null일 때 댓글 출력, 텅 비어있어야 함");

        }
    }
}