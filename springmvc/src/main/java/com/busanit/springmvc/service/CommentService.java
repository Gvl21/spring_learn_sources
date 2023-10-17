package com.busanit.springmvc.service;

import com.busanit.springmvc.dto.CommentDto;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.entity.Comment;
import com.busanit.springmvc.repository.ArticleRepository;
import com.busanit.springmvc.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleid) {
        return commentRepository.findByArticleId(articleid)
                .stream()
                .map(CommentDto::createCommentDto)
                .toList();

        /** for 문을 사용하는 방법
       // 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleid);
        // 엔티티 -> DTO로 변환
        ArrayList<CommentDto> commentDtos = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            CommentDto commentDto = CommentDto.createCommentDto(comment);
            commentDtos.add(commentDto);
        }
        // 결과반환
        return commentDtos;*/
        // 스트림의 특징 : 원본 데이터를 변경하지 않는다. 정렬된 결과를 배열에 담아 반환, 내부 반복문
    }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 예외 처리
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다. 댓글 생성 실패"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        // DB에 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변환 컨트롤러로 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB 갱신
        Comment updated = commentRepository.save(target);
        // 4, DTO 로 변환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패"));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
