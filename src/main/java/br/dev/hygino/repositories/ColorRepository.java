package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Integer> {

}
