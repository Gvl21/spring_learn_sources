package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;            // 댓글의 아이디 PK
    private Long articleId;       // 게시글의 아이디 FK
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(comment.getId(),comment.getArticle().getId(), comment.getNickname(), comment.getBody());
    }
}
