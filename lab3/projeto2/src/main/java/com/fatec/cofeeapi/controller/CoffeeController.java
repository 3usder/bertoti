package com.fatec.cofeeapi.controller;

import com.fatec.cofeeapi.model.Coffee;
import com.fatec.cofeeapi.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeService service;

    @GetMapping
    public List<Coffee> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable String id) {
        return service.findById(id)
                .map(coffee -> ResponseEntity.ok().body(coffee))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coffee create(@RequestBody Coffee coffee) {
        return service.save(coffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable String id, @RequestBody Coffee coffee) {
        return service.findById(id)
                .map(existingCoffee -> {
                    existingCoffee.setName(coffee.getName());
                    Coffee updated = service.save(existingCoffee);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}