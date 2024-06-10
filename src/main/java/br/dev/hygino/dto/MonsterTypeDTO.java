package br.dev.hygino.dto;

import br.dev.hygino.entities.MonsterType;

public record MonsterTypeDTO(
        Integer id,
        String typeName) {
            
    public MonsterTypeDTO(MonsterType entity) {
        this(entity.getId(), entity.getTypeName());
    }
}
