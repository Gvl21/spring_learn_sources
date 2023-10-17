package com.busanit.springmvc.service;

import com.busanit.springmvc.dto.PizzaDto;
import com.busanit.springmvc.entity.Pizza;
import com.busanit.springmvc.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    public Pizza createMenu(PizzaDto dto) {
        Pizza created = dto.toEntity();
        if (dto.getId() != null){
            return null;
        }
        Pizza createdMenu = pizzaRepository.save(created);
        return createdMenu;
    }

    public Pizza show(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 피자가 없어요"));
        return target;
    }

    public List<Pizza> index() {
        List<Pizza> menus = pizzaRepository.findAll();
        return menus;
    }

    public Pizza update(Long id, PizzaDto dto) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 id에요"));
        target.patch(dto);
        Pizza updated = pizzaRepository.save(target);
        return updated;

    }

    public Pizza delete(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없어요"));
        pizzaRepository.delete(target);
        return target;
    }
}
