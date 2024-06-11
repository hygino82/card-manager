package br.dev.hygino.dto;

import jakarta.validation.constraints.NotNull;

public record InsertAtributeDTO(@NotNull String name) {
}