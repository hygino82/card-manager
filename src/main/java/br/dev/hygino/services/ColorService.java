package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.ColorDTO;
import br.dev.hygino.dto.InsertColorDTO;
import br.dev.hygino.entities.Color;
import br.dev.hygino.repositories.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService implements IService<InsertColorDTO, ColorDTO> {

    private final ColorRepository repository;

    @Override
    @Transactional
    public ColorDTO insert(@Valid InsertColorDTO dto) {
        Color entity = new Color();
        dtoToEntity(dto, entity);
        return new ColorDTO(repository.save(entity));
    }

    private void dtoToEntity(@Valid InsertColorDTO dto, Color entity) {
        entity.setName(dto.name());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColorDTO> findAll(Pageable pageable) {
        Page<Color> page = repository.findAll(pageable);
        return page.map(ColorDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ColorDTO findById(Integer id) {
        Color entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        return new ColorDTO(entity);
    }

    @Override
    @Transactional
    public ColorDTO update(Integer id, @Valid InsertColorDTO dto) {
        try {
            Color entity = repository.getReferenceById(id);
            dtoToEntity(dto, entity);
            return new ColorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Id: " + id + " not found!");
        }
    }

    @Override
    public void delete(Integer id) {
        Color entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        repository.delete(entity);
    }
}
