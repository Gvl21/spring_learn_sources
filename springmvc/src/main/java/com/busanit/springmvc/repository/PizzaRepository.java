package com.busanit.springmvc.repository;

import com.busanit.springmvc.entity.Comment;
import com.busanit.springmvc.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    @Query(value = """
            SELECT *
            FROM PIZZA
            WHERE NAME = :name""",
            nativeQuery = true)
    List<Comment> findByName(String name);
}
