package br.dev.hygino.dto;

import br.dev.hygino.entities.Atribute;

public record AtributeDTO(
        Integer id,
        String name) {
    public AtributeDTO(Atribute entity) {
        this(entity.getId(), entity.getName());
    }
}