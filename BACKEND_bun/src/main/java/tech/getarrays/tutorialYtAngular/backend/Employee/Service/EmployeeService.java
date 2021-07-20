package tech.getarrays.tutorialYtAngular.backend.Employee.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.tutorialYtAngular.dto.EmployeeDto;
import tech.getarrays.tutorialYtAngular.exeption.UserNotFoundException;
import tech.getarrays.tutorialYtAngular.model.Employee;
import tech.getarrays.tutorialYtAngular.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper mapper;


    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper mapper) {
        this.employeeRepo = employeeRepo;
        this.mapper = mapper;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto){
        Employee employee = mapToEntity(employeeDto);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        Employee employee2 = employeeRepo.save(employee);
        return entityToDto(employee2);
    }

    public List<EmployeeDto> findAllEmployees(){

        List<EmployeeDto> employeDtoList =new ArrayList<>();
        List<Employee> employees = employeeRepo.findAll();
        for(Employee employee: employees){
           employeDtoList.add(entityToDto(employee));
        }
        return employeDtoList;
    }

    public EmployeeDto updateEmployee(EmployeeDto employee){

        Employee forUpdate = DtoToEntity(employee);

       return entityToDto(employeeRepo.save(forUpdate));
    }

    public void deleteEmployee(Long id){

        Employee employee = findEmployeeById2(id);
        employeeRepo.delete(employee);
     //      employeeRepo.deleteEmployeeById(id);
    }

    public EmployeeDto findEmployeeById(Long id){
        Employee emp= employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " not found"));
        return mapToDTO(emp);
    }
    public Employee findEmployeeById2(Long id){
         return employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " not found"));

    }
/*
    public void deleteEmployeeNou(Long id){
        Employee employee = findEmployeeById(id);
        employeeRepo.delete(employee);
    }
*/


    public List<EmployeeDto> mapToDTOList(List<Employee> employees){
        List<EmployeeDto> dtos = employees.stream().map(student -> mapper.map(student, EmployeeDto.class)).collect(Collectors.toList());
        return dtos;
    }
    public EmployeeDto mapToDTO(Employee employee){
        EmployeeDto dto=mapper.map(employee, EmployeeDto.class);
        return dto;
    }
    public Employee mapToEntity(EmployeeDto dto){
        Employee employee =mapper.map(dto, Employee.class);
        return employee;
    }// trebuie verifaicatre si sterse duplicatele
    private EmployeeDto entityToDto(Employee employee){
        return mapper.map(employee, EmployeeDto.class);    }

    public Employee DtoToEntity(EmployeeDto employeeDto){
        return mapper.map(employeeDto, Employee.class);
    }


}
