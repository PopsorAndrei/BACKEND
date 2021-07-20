package tech.getarrays.tutorialYtAngular;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.tutorialYtAngular.backend.Employee.Service.EmployeeService;
import tech.getarrays.tutorialYtAngular.dto.EmployeeDto;
import tech.getarrays.tutorialYtAngular.model.Employee;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List <EmployeeDto> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeesById(@PathVariable("id") Long id){
        EmployeeDto employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping ("/")//aici erea employee/add
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto newEmployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto,HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee( @PathVariable("id") Long id,
                                                       @RequestBody EmployeeDto employeeDto){
        EmployeeDto newEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
