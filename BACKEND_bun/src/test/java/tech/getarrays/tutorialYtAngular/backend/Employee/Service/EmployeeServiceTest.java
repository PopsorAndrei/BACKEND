package tech.getarrays.tutorialYtAngular.backend.Employee.Service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import tech.getarrays.tutorialYtAngular.dto.EmployeeDto;
import tech.getarrays.tutorialYtAngular.exeption.UserNotFoundException;
import tech.getarrays.tutorialYtAngular.model.Employee;
import tech.getarrays.tutorialYtAngular.repo.EmployeeRepo;


import java.util.Optional;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {


    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepo employeeRepo;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void addEmployeeTest() {


        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(100L);
        employeeDto.setEmployeeCode("AAAsasdas");
        employeeDto.setEmail("spersamearga@gmail.com");
        employeeDto.setImageURL("ssssss.jpg");
        employeeDto.setName("Andrei");
        employeeDto.setPhone("1234567890");

        EmployeeDto employeeDtoBun = new EmployeeDto();
        employeeDto.setId(200L);

        when(modelMapper.map(any(EmployeeDto.class), eq(Employee.class))).thenReturn(new Employee());
        when(employeeRepo.save(any(Employee.class))).thenReturn(new Employee());
        when(modelMapper.map(any(Employee.class), eq(EmployeeDto.class))).thenReturn(employeeDtoBun);

        EmployeeDto response = employeeService.addEmployee(employeeDto);
        verify(employeeRepo).save(any(Employee.class));
        assertNotNull(response);
        assertEquals(employeeDtoBun, response);


    }
   @Test
    public void deleteEmployeeTest(){

       Employee employee = new Employee();
        employee.setId(10L);

        when(employeeRepo.findEmployeeById(anyLong())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(10L);
        verify(employeeRepo).delete(employee);


    }

    @Test
    public void findAllEmployees() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(100L);
        employeeDto.setEmployeeCode("AAAsasdas");
        employeeDto.setEmail("spersamearga@gmail.com");
        employeeDto.setImageURL("ssssss.jpg");
        employeeDto.setName("Andrei");
        employeeDto.setPhone("1234567890");

        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto.setId(100L);
        employeeDto.setEmployeeCode("AAAsasdas");
        employeeDto.setEmail("spersamearga@gmail.com");
        employeeDto.setImageURL("ssssss.jpg");
        employeeDto.setName("Andrei");
        employeeDto.setPhone("1234567890");



    }

    @Test
    public void updateEmployeeTest() {
    EmployeeDto employeeDto = new EmployeeDto();
    employeeDto.setId(100L);
    employeeDto.setEmployeeCode("AAAsasdas");
    employeeDto.setEmail("spersamearga@gmail.com");
    employeeDto.setImageURL("ssssss.jpg");
    employeeDto.setName("Andrei");
    employeeDto.setPhone("1234567890");

    Employee employee = new Employee();
   /* employee.setId(100L);
    employee.setEmployeeCode("AAAA");
    employee.setEmail("speeer");
    employee.setImageURL("aaaa.jpg");
    employee.setName("bogdan");
    employee.setPhone("0987654321");*/

    when(employeeRepo.save(any(Employee.class))).thenReturn(employee);
    when(modelMapper.map(employeeDto, Employee.class)).thenReturn(employee);
    when(modelMapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);

   EmployeeDto result = employeeService.updateEmployee(employeeDto);

    verify(employeeRepo).save(any(Employee.class));

    assertEquals(employeeDto,result);

    }

    @Test(expected = UserNotFoundException.class)
    public void deleteEmployee_shouldThrowException() {
        Employee employee = new Employee();
        employee.setId(100L);
        employee.setEmail("speeer");
        employee.setImageURL("aaaa.jpg");
        employee.setName("bogdan");
        employee.setPhone("0987654321");
        employeeService.deleteEmployee(employee.getId());
        verify(employeeRepo).deleteEmployeeById(employee.getId());
    }



    @Test
    public void findEmployeeById() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(100L);
        employeeDto.setEmployeeCode("AAAsasdas");
        employeeDto.setEmail("spersamearga@gmail.com");
        employeeDto.setImageURL("ssssss.jpg");
        employeeDto.setName("Andrei");
        employeeDto.setPhone("1234567890");

        Employee employee = new Employee();
        employee.setId(100L);
        employee.setEmail("speeer");
        employee.setImageURL("aaaa.jpg");
        employee.setName("bogdan");
        employee.setPhone("0987654321");

        when(employeeRepo.findEmployeeById(anyLong())).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee,EmployeeDto.class)).thenReturn(employeeDto);
        EmployeeDto employeeDtoById = employeeService.findEmployeeById(100L);

        EmployeeDto result = employeeService.findEmployeeById(100L);


        assertEquals(employeeDto,result);
    }

    @Test
    public void mapToDTO() {
    }

    @Test
    public void mapToEntity() {
    }
}