package com.example.application.data.service;

import com.example.application.data.entity.Apartamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApartamentosRepository
        extends
            JpaRepository<Apartamentos, Long>,
            JpaSpecificationExecutor<Apartamentos> {

}
