package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.Medicine;
import pl.zagorski.domain.PurchaseOrder;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    void edit(Employee employee);
    List<Employee> findAll();
    Employee findOne(int id);
    Employee getEmployeeByLogin(@Param("login") String login);
    List<Employee> getEmployeesByName(@Param("name") String name);
    List<Employee> getEmployeesBySurname(@Param("surname") String surname);
    List<Employee> getEmployeesByNameAndSurname(@Param("name") String name,@Param("surname") String surname);
    List<Object[]> showAllEmployees();
    List<Object[]> showAndOrderBySurname();
    List<Object[]> showEmployeesByName(@Param("name") String name);
    List<Object[]> showEmployeesBySurname(@Param("surname") String surname);
    List<Object[]> showEmployeesByNameAndSurname(@Param("name") String name,@Param("surname") String surname);
    List<String> showActionsOfThisEmployee(String login);



}
