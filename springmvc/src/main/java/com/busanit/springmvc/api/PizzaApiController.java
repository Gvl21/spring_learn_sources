package com.busanit.springmvc.api;

import com.busanit.springmvc.dto.PizzaDto;
import com.busanit.springmvc.entity.Pizza;
import com.busanit.springmvc.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PizzaApiController {
    private final PizzaService pizzaService;
    // 생성
    @PostMapping("/api/pizza")
    public ResponseEntity<Pizza> create(@RequestBody PizzaDto dto){
        Pizza newMenu = pizzaService.createMenu(dto);
        return (newMenu != null)
               ? ResponseEntity.status(HttpStatus.OK).body(newMenu)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    // 조회(단건)
    @GetMapping("api/pizza/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Long id){
        Pizza pizza = pizzaService.show(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }
    // 조회(일괄)
    @GetMapping("/api/pizza")
    public ResponseEntity<List<Pizza>> index(){
        List<Pizza> index = pizzaService.index();
        return ResponseEntity.status(HttpStatus.OK).body(index);
    }

    // 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody PizzaDto dto){
        Pizza updatedMenu = pizzaService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMenu);
    }

    // 삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Long id){
        Pizza deleted = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);

    }


}
