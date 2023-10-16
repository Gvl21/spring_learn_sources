package com.busanit.springmvc.service;

import com.busanit.springmvc.dto.CoffeeDto;
import com.busanit.springmvc.entity.Coffee;
import com.busanit.springmvc.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor        // 생성자 의존성 주입
@Service        // 스프링 관리 객체 선언
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        // ID 값이 이미 존재하고 있는 커피를 생성하려고 할 경우 (DTO에 id가 있는 경우)
        if (coffee.getId() != null) {
            // 잘못된 요청 응답 객체 반환
            return null;
        }
        // 성공적으로 생성했을 경우
        Coffee created = coffeeRepository.save(coffee);
        return created;
    }

    public Coffee update(Long id, CoffeeDto dto) {
        // 1. dto -> entity
        Coffee coffee = dto.toEntity();
        // DB에서 n번 커피 엔티티를 가져온다.
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 예외처리
        if (target == null) {
            log.info("잘못된 요청입니다. 타겟 데이터가 비어있습니다.");
            return null;
        }

        // 2. entity 수정 (DB에서 가져온 엔티티를 DTO에서 가져온 엔티티로 수정)
        target.patch(coffee);
        // 3. 저장
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        // 삭제할 타겟을 DB에서 조회
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 예외처리 : 타겟이 존재하지 않을 때
        if (target == null) {
            log.info("id번호로 삭제할 엔티티가 없습니다.");
            return null;
        }
        // 타겟을 DB에서 삭제
        coffeeRepository.delete(target);
        return target;
    }
}
