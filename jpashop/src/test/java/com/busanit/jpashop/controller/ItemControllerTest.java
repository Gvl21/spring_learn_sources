package com.busanit.jpashop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("ADMIN 권한으로 상품등록 페이지 접근했을 때")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))  // 상품 등록 페이지
                .andDo(print())                     // 로그 출력
                .andExpect(status().isOk());        // 테스트 검증해줘 isOk() 200 응답 맞는지
    }
    @Test
    @DisplayName("USER 권한으로 상품등록 페이지 접근했을 때")
    @WithMockUser(username = "user", roles = "USER")
    void user() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))  // 상품 등록 페이지
                .andDo(print())                     // 로그 출력
                .andExpect(status().is4xxClientError());        // 테스트 검증해줘 is4xxClientError() 4xx 응답 맞는지
    }
    @Test
    @DisplayName("anonymous로 상품등록 페이지 접근했을 때")
    void anonymous() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/item/new"))  // 상품 등록 페이지
                .andDo(print())                     // 로그 출력
                .andExpect(status().is3xxRedirection());        // 테스트 검증해줘 is4xxClientError() 3xx 응답 맞는지
    }       // 유저상태가 아닐때(로그인 되지 않은 상태)에서 해당 API 접근시 redirect로 로그인 페이지로 이동시킨다.
            // config 참고


}