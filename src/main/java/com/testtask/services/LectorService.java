package com.testtask.services;

import com.testtask.entities.Lector;
import com.testtask.repositories.LectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorService implements SimpleService<Lector> {
    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public Lector save(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public Lector getById(Long id) {
        return lectorRepository.getOne(id);
    }

    @Override
    public List<Lector> getAll() {
        return lectorRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        lectorRepository.delete(lectorRepository.getOne(id));
    }
}
