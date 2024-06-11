package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.AtributeDTO;
import br.dev.hygino.dto.InsertAtributeDTO;
import br.dev.hygino.entities.Atribute;
import br.dev.hygino.repositories.AtributeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtributeService implements IService<InsertAtributeDTO, AtributeDTO> {

    private final AtributeRepository repository;

    @Override
    @Transactional
    public AtributeDTO insert(@Valid InsertAtributeDTO dto) {
        Atribute entity = new Atribute();
        dtoToEntity(dto, entity);
        return new AtributeDTO(repository.save(entity));
    }

    private void dtoToEntity(@Valid InsertAtributeDTO dto, Atribute entity) {
        entity.setName(dto.name());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AtributeDTO> findAll(Pageable pageable) {
        Page<Atribute> page = repository.findAll(pageable);
        return page.map(AtributeDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public AtributeDTO findById(Integer id) {
        Atribute entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        return new AtributeDTO(entity);
    }

    @Override
    @Transactional
    public AtributeDTO update(Integer id, @Valid InsertAtributeDTO dto) {
        try {
            Atribute entity = repository.getReferenceById(id);
            dtoToEntity(dto, entity);
            return new AtributeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Id: " + id + " not found!");
        }
    }

    @Override
    public void delete(Integer id) {
        Atribute entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        repository.delete(entity);
    }
}
