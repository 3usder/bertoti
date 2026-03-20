package com.fatec.cofeeapi.service;

import com.fatec.cofeeapi.model.Coffee;
import com.fatec.cofeeapi.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository repository;

    public List<Coffee> findAll() {
        return repository.findAll();
    }

    public Optional<Coffee> findById(String id) {
        return repository.findById(id);
    }

    public Coffee save(Coffee coffee) {
        return repository.save(coffee);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}