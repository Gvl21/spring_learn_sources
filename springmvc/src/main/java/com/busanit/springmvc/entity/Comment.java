package com.busanit.springmvc.entity;

import com.busanit.springmvc.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor      // 기본 생성자
@AllArgsConstructor     // 전체 파라미터 생성자
@Getter
@ToString
@Entity // 해당 클래스의 필드를 바탕으로 DB에 테이블 생성
public class Comment {
    @Id                 // 대표키 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // DB에서 자동으로 숫자 증가
    private Long id;
    @ManyToOne          // 다대일 관계 (Comment to Article)
    @JoinColumn(name = "article_id")         // 외래키 FK 지정, 생략 가능
    private Article article;
    @Column(name = "nickname")
    private String nickname;
    @Column
    private String body;

    // dto -> entity 생성 메서드
    public static Comment createComment(CommentDto dto, Article article) {
        // dto의 아이디가 존재하는 경우
        if (dto.getId() != null){
            throw new IllegalArgumentException("기존 댓글이 있어 댓글 생성에 실패했습니다.");
        }
        if (dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("게시글 id가 잘못되었습니다.");
        }
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto){
        // 예외처리
        if (this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패, 잘못된 ID입니다.");
        }
        // 객체 업데이트
        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }
        if(dto.getBody() != null){
            this.body = dto.getBody();

        }


    }
}
