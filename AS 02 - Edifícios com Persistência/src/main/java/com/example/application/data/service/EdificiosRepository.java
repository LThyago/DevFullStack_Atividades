package com.example.application.data.service;

import com.example.application.data.entity.Edificios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EdificiosRepository extends JpaRepository<Edificios, Long>, JpaSpecificationExecutor<Edificios> {

}
