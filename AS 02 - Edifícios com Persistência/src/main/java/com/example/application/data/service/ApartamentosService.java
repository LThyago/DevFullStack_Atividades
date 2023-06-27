package com.example.application.data.service;

import com.example.application.data.entity.Apartamentos;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ApartamentosService {

    private final ApartamentosRepository repository;

    public ApartamentosService(ApartamentosRepository repository) {
        this.repository = repository;
    }

    public Optional<Apartamentos> get(Long id) {
        return repository.findById(id);
    }

    public Apartamentos update(Apartamentos entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Apartamentos> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Apartamentos> list(Pageable pageable, Specification<Apartamentos> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
