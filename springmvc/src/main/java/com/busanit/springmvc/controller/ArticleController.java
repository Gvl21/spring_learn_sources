package com.busanit.springmvc.controller;

import com.busanit.springmvc.dto.ArticleForm;
import com.busanit.springmvc.dto.CommentDto;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.repository.ArticleRepository;
import com.busanit.springmvc.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// 생성자를 통한 의존성 주입
@Controller
@RequiredArgsConstructor    //
@Slf4j    // 로깅기능을 위한 애노테이션
public class ArticleController {
    private final CommentService commentService;

    // @Autowired  // 스프링부트에서 스프링 컨테이너에 의존성 주입 (필드 주입) + 가능하면 생성자를 통한 주입을 하는 게 좋다.
    private final ArticleRepository articleRepository;

    //@Autowired  // 생성자를 통한 의존성 주입     // 롬복 생성자 애노테이션으로 대체 가능
//    public ArticleController(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }   // 스프링에서 생성자가 하나만 있을 때, @Autowired가 없어도 자동으로 DI를 한다.

    @GetMapping("/articles/new")
    public String newArticleForm(){
//        Logger logger = LoggerFactory.getLogger(ArticleController.class);
        return "articles/new";
    }
    // CREATE
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm dto){
        // 폼 데이터를 DTO로 전달받아 확임
        log.info(dto.toString());
        // 1. DTO를 도메인 Entity로 변환
        Article article = dto.toEntity();
        // 2. Repository(DAO)로 Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        // 리다이렉트를 통해 새로운 주소 요청
        // "redirect:URL_주소
        return "redirect:/articles/" + saved.getId();
    }
    //  READ
    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id, Model model){
        // URL 경로 상에 잇는 변수를 애노테이션으로 확인
//        log.info("id : " + id);
        // 1. id 조회하여 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        List<CommentDto> comments = commentService.comments(id);
        // 2. 모델에 데이터를 등록하기
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        // 3. 뷰 페이지 View Resolver
        return "articles/show";
    }
    //  READ ALL
    @GetMapping("articles")
    public String showAll(Model model){
        // 1. id 조회하여 데이터 가져오기
        List<Article> articles = articleRepository.findAll();
        // 2. 모델에 데이터를 등록하기
        model.addAttribute("articleList", articles);
        // 3. 뷰 페이지 View Resolver
        return "articles/index";
    }

    // UPDATE
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", article);
        // View Resolve
        return "articles/edit";
    }

    // UPDATE
    @PostMapping("articles/{id}/update")
    public String updateArticle(ArticleForm dto, @PathVariable Long id){
        log.info(dto.toString());
        // 1. dto를 엔티티로 변환
        Article article = dto.toEntity();
        // 2. 엔티티를 db에 저장
        // 2-1. db에서 기존 데이터를 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        // 2-2. 데이터 갱신하기
        if(target != null){
            article.setId(id);
            articleRepository.save(article);
        }

        // 3. 결과 페이지 리다이렉트
        return "redirect:/articles/" + id;
    }

    // DELETE
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        log.info("삭제요청" + id);
        // 1. 삭제할 대상 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 대상 엔티티 삭제하기
        if(article != null){
            articleRepository.delete(article);
            // 리다이렉트 속성에 휘발성 속성 추가
            attributes.addFlashAttribute("msg", "삭제되었습니다.");
        }
        // 3. 리다이렉트
        return "redirect:/articles";
    }
}
