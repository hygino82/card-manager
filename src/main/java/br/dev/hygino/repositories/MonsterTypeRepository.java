package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.MonsterType;

public interface MonsterTypeRepository extends JpaRepository<MonsterType, Integer> {

}
