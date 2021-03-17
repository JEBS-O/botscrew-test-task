package com.testtask.services;

import com.testtask.entities.Department;
import com.testtask.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements SimpleService<Department> {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getOne(id);
    }

    public Department getByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        departmentRepository.delete(departmentRepository.getOne(id));
    }
}
