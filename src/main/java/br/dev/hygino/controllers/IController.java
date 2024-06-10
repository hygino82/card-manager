package br.dev.hygino.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

public interface IController<E, S> {
    ResponseEntity<S> insert(@Valid E dto);

    ResponseEntity<Page<S>> findAll(Pageable pageable);

    ResponseEntity<S> findById(Integer id);

    ResponseEntity<S> update(Integer id, @Valid E dto);

    ResponseEntity<Void> delete(Integer id);
}
