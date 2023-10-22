package com.busanit.jpashop.repository;

import com.busanit.jpashop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    /* 스프링 공식문서 : 쿼리메소드
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    * */


    // 쿼리 메소드
    // find + (엔티티 이름:생략가능) + By + (변수 이름:필드명)
    // select * from item where itemnm = ?
    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemDetail(String itemNm);


    // select * from item where price < ?
    List<Item> findByPriceLessThan(Integer price);

    //select * from item where ItemNm or ItemDetail
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 가격순으로 내림차순 조회, 조건 :특정 가격보다 적은 경우 => 쿼리메소드
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // @Query Method 사용 : JPQL, native Query
    // 상품 상세명에 포함된 글자를 기준으로 가격기준 내림차순
    @Query(value = "select * from item " +
            "where item_detail " +
            "like %:itemDetail% " +
            "order by price desc", nativeQuery = true)
    List<Item> findByIemDetailNative(@Param("itemDetail") String itemDetail);

    // JPQL은 SQL과 유사하지만 다른부분이 있으므로 유의해야함, 검색대상이 엔티티(DB가 아님)
    // 문법 유사 -> 결국은 SQL로 변환된다.
    // 특징 : 틀정 공급업체에 종속적이지 않고, 추상화 ㅗ디어있다.
    // 별칭(alias)가 필수
    // SQL 키워드는 대소문자 구분하지 않는다.
    // 엔티티, 필드는 대소문자 구분한다.
    @Query(value = "select i.itemDetail from Item i " +
            "where i.itemDetail " +
            "like %:itemDetail% " +
            "order by i.price desc" )
    List<String> findByItemDetailJPQL(@Param("itemDetail") String itemDetail);

}
