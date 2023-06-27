package com.example.application.data.service;

import com.example.application.data.entity.Moradores;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MoradoresService {

    private final MoradoresRepository repository;

    public MoradoresService(MoradoresRepository repository) {
        this.repository = repository;
    }

    public Optional<Moradores> get(Long id) {
        return repository.findById(id);
    }

    public Moradores update(Moradores entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Moradores> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Moradores> list(Pageable pageable, Specification<Moradores> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
