package com.busanit.jpashop.controller;

import com.busanit.jpashop.dto.CartItemDto;
import com.busanit.jpashop.repository.CartItemRepository;
import com.busanit.jpashop.repository.CartRepository;
import com.busanit.jpashop.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    // CREATE : 장바구니 담기
    @PostMapping(value = "/cart")
    @ResponseBody
    public ResponseEntity addCart(@RequestBody @Valid CartItemDto cartItemDto,
                                  Principal principal, BindingResult bindingResult){
        // 예외처리 : 유효성 검증 에러 발생시 에러 메시지와 함께 400 리턴
        if (bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                msg.append(fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.toString());
        }
        // 서비스 위임
        String email = principal.getName();
        Long cartItemId;
        try {
            cartItemId = cartService.addCart(cartItemDto, email);
        } catch (Exception e) {
            // 서비스 계층 예외 발생시 -> 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartItemId);
    }

    @GetMapping(value = {"/cart","/cart/{page}"})
    public String cart(Model model, Principal principal, @PathVariable("page") Optional<Integer> page){
        // 페이지 객체
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        String email = principal.getName();
        cartService.getCartItem();
        model.addAttribute("");
        return null;
    }
}
