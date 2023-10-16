package com.busanit.springmvc.api;

import com.busanit.springmvc.dto.CoffeeDto;
import com.busanit.springmvc.entity.Coffee;
import com.busanit.springmvc.repository.CoffeeRepository;
import com.busanit.springmvc.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController     // REST API 선언
@RequiredArgsConstructor // 생성자 의존성 주입
public class CoffeeApiController {
    private final CoffeeService coffeeService;

    // GET
    // id를 기준으로 하나를 조회
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return (coffee != null)
                ? ResponseEntity.status(HttpStatus.OK).body(coffee)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 전체 리스트를 조회
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index() {
        return coffeeService.index();
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto) {
        // 서비스에 위임
        Coffee created = coffeeService.create(coffeeDto);
        // 성공 응답객체 반환
        return (created != null)
                ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto) {
        Coffee updated = coffeeService.update(id, dto);
        // 4. 응답
        return (updated != null)
               ? ResponseEntity.status(HttpStatus.OK).body(updated)
               : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        // 응답 객체 반환
        return (deleted != null)
                ? ResponseEntity.status(HttpStatus.OK).body(null)
               : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }




}
