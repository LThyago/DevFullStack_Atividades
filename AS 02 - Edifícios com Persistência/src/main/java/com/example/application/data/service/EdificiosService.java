package com.example.application.data.service;

import com.example.application.data.entity.Edificios;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EdificiosService {

    private final EdificiosRepository repository;

    public EdificiosService(EdificiosRepository repository) {
        this.repository = repository;
    }

    public Optional<Edificios> get(Long id) {
        return repository.findById(id);
    }

    public Edificios update(Edificios entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Edificios> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Edificios> list(Pageable pageable, Specification<Edificios> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
