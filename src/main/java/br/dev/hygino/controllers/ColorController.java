package br.dev.hygino.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.ColorDTO;
import br.dev.hygino.dto.InsertColorDTO;
import br.dev.hygino.services.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController implements IController<InsertColorDTO, ColorDTO> {

    private final ColorService service;

    @Override
    @PostMapping
    public ResponseEntity<ColorDTO> insert(@Valid @RequestBody InsertColorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ColorDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> update(@PathVariable Integer id,
            @Valid @RequestBody InsertColorDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
