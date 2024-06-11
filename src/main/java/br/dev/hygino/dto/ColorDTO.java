package br.dev.hygino.dto;

import br.dev.hygino.entities.Color;

public record ColorDTO(
        Integer id,
        String name) {
    public ColorDTO(Color entity) {
        this(entity.getId(), entity.getName());
    }
}