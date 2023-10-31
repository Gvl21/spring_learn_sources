package com.busanit.jpashop.repository;

import com.busanit.jpashop.constant.ItemSellStatus;
import com.busanit.jpashop.dto.ItemSearchDto;
import com.busanit.jpashop.entity.Item;
import com.busanit.jpashop.entity.QItem;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.busanit.jpashop.entity.QItem.item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        // QueryDSL + Paging
        // Query Domain-Specific Language (도메인 특화 언어, 특정 작업에 최적화된 언어)
        // Qitem item = new Qitem("item"); => Qitem.item => 정적 임포트를 통해 => item
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(regDateAfter(itemSearchDto.getSearchDateType()),             // 1. 등록일 기준
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),    // 2. 상품상태 조건
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))  // 3. 검색어 질의
                .orderBy(item.id.desc())        // 최신순으로 내림차순 설정
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 페이지의 전체 길이 구하기
        Long total = queryFactory
                .select(item.count())
                .from(item)
                .where(regDateAfter(itemSearchDto.getSearchDateType()),             // 1. 등록일 기준
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),    // 2. 상품상태 조건
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))  // 3. 검색어 질의
                .fetchOne();
        // 페이지 타입의 구현체 객체로 반환
        return new PageImpl<>(itemList, pageable, total);
    }

    // 검색어를 포함하고 있는 상품 또는 상품 생성자에 따라 조회하는 조건절 만들어주는 메서드
    private Predicate searchByLike(String searchBy, String searchQuery) {
        // 상품명 포함 질의어 조회
        if (StringUtils.equals("itemNm", searchBy)){
            return item.itemNm.like("%" + searchQuery + "%");
            // 등록자별 질의어 조회
        } else if (StringUtils.equals("createdBy", searchBy)){
            return item.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    // 상품판매상태 조건절을 만들어주는 메서드
    private Predicate searchSellStatusEq(ItemSellStatus searchSellStatus) {
        if (searchSellStatus == null) return null;              // 판매상태 전체 => 조건절 없이
        else return item.itemSellStatus.eq(searchSellStatus);          // SELL, SOLD_OUT
    }

    
    // 등록일 조건절을 만들어주는 메서드
    private Predicate regDateAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();
        // 만약 날짜 타입이 전체 혹은 비어있다면
        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {    // "all".equals(searchDateType)
            return null;         // null을 리턴하면 where조건을 생략한다.
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        } return item.regTime.after(dateTime);
    }
}
