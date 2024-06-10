package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

public interface IService<E, S> {

    S insert(@Valid E dto);

    Page<S> findAll(Pageable pageable);

    S findById(Integer id);

    S update(Integer id, @Valid E dto);

    void delete(Integer id);
}
