package tech.getarrays.tutorialYtAngular.Service;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.tutorialYtAngular.exeption.UserNotFoundException;
import tech.getarrays.tutorialYtAngular.model.Employee;
import tech.getarrays.tutorialYtAngular.repo.EmployeeRepo;

import java.util.List;
import java.util.UUID;


@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id){

        Employee employee = findEmployeeById(id);
        employeeRepo.delete(employee);
        //   employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " not found"));
    }

    public void deleteEmployeeNou(Long id){
        Employee employee = findEmployeeById(id);
        employeeRepo.delete(employee);
    }
}
