package com.testtask.services;

import com.testtask.entities.Department;
import com.testtask.entities.Lector;
import com.testtask.entities.enums.Degree;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandService {
    private final LectorService lectorService;
    private final DepartmentService departmentService;

    public CommandService(LectorService lectorService, DepartmentService departmentService) {
        this.lectorService = lectorService;
        this.departmentService = departmentService;
    }

    public String getAnswer(String command) {
        if(command.matches("\\AWho is head of department [\\D\\s]+"))
            return getHeadOfDepartment(command);
        if(command.matches("\\AShow [\\D\\s]+ statistics$"))
            return showStatistics(command);
        if(command.matches("\\AShow the average salary for the department [\\D\\s]+"))
            return showAverageSalary(command);
        if(command.matches("\\AShow count of employee for [\\D\\s]+"))
            return showCountOfEmployee(command);
        if(command.matches("\\AGlobal search by [\\D\\s]+"))
            return globalSearch(command);
        return "This command is not exist";
    }

    private String getHeadOfDepartment(String command) {
        String departmentName = command.substring(26);
        Department department = departmentService.getByName(departmentName);
        if(department == null) {
            return "Department is not exist";
        } else {
            return "Head of " + department.getName() + " department is " + department.getHeadOfDepartment().getFirstName() + " " + department.getHeadOfDepartment().getSecondName();
        }
    }

    private String showStatistics(String command) {
        String departmentName = command.substring(5, command.length()-11);
        Department department = departmentService.getByName(departmentName);
        if(department == null) {
            return "Department is not exist";
        } else {
            List<Lector> employees = department.getEmployees();
            long numberOfAssistantsInDepartment = employees.stream().filter(e -> e.getDegree() == Degree.ASSISTANT).count();
            long numberOfAssociateProfessorsInDepartment = employees.stream().filter(e -> e.getDegree() == Degree.ASSOCIATE_PROFESSOR).count();
            long numberOfProfessorsInDepartment = employees.stream().filter(e -> e.getDegree() == Degree.PROFESSOR).count();
            return "assistants - " + numberOfAssistantsInDepartment +
                    "\nassociate professors - " + numberOfAssociateProfessorsInDepartment +
                    "\nprofessors - " + numberOfProfessorsInDepartment;
        }
    }

    private String showAverageSalary(String command) {
        String departmentName = command.substring(43);
        Department department = departmentService.getByName(departmentName);
        if(department == null) {
            return "Department is not exist";
        } else {
            double sumSalary = department.getEmployees().stream().mapToDouble(Lector::getSalary).sum();
            return String.format("The average salary of %s is %.2f", department.getName(), sumSalary/department.getEmployees().size());
        }
    }

    private String showCountOfEmployee(String command) {
        String departmentName = command.substring(27);
        Department department = departmentService.getByName(departmentName);
        return department != null ? String.valueOf(department.getEmployees().size()) : "Department is not exist";
    }

    private String globalSearch(String command) {
        String template = command.substring(17);
        StringBuilder builder = new StringBuilder();
        lectorService.getAll().stream()
                .filter(e -> e.getFirstName().contains(template) || e.getSecondName().contains(template))
                .forEach(e -> builder.append(e.getFirstName()).append(" ").append(e.getSecondName()).append("\n"));
        return builder.toString();
    }
}