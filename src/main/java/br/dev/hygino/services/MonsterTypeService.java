package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.InsertMonsterTypeDTO;
import br.dev.hygino.dto.MonsterTypeDTO;
import br.dev.hygino.entities.MonsterType;
import br.dev.hygino.repositories.MonsterTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonsterTypeService implements IService<InsertMonsterTypeDTO, MonsterTypeDTO> {
    private final MonsterTypeRepository repository;

    @Override
    @Transactional
    public MonsterTypeDTO insert(@Valid InsertMonsterTypeDTO dto) {
        MonsterType entity = new MonsterType();
        dtoToEntity(dto, entity);
        return new MonsterTypeDTO(repository.save(entity));
    }

    private void dtoToEntity(@Valid InsertMonsterTypeDTO dto, MonsterType entity) {
        entity.setTypeName(dto.typeName());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MonsterTypeDTO> findAll(Pageable pageable) {
        Page<MonsterType> page = repository.findAll(pageable);
        return page.map(MonsterTypeDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public MonsterTypeDTO findById(Integer id) {
        MonsterType entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        return new MonsterTypeDTO(entity);
    }

    @Override
    @Transactional
    public MonsterTypeDTO update(Integer id, @Valid InsertMonsterTypeDTO dto) {
        try{
            MonsterType entity = repository.getReferenceById(id);
            dtoToEntity(dto, entity);
            return new MonsterTypeDTO(repository.save(entity));
        }
        catch(EntityNotFoundException e){
           throw new IllegalArgumentException("Id: " + id + " not found!"); 
        }
    }

    @Override
    public void delete(Integer id) {
        MonsterType entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id: " + id + " not found!"));
        repository.delete(entity);
    }
}
