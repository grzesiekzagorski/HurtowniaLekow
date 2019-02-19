package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeImpl {

    void save(Employee employee);
    void edit(Employee employee);
    List<Employee> findAll();
    Employee findOne(int id);
    Optional<Employee> getEmployeeByLogin(@Param("login") String login);
    List<Employee> getEmployeesByName(@Param("name") String name);
    List<Employee> getEmployeesBySurname(@Param("surname") String surname);
    List<Employee> getEmployeesByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
    List<Object[]> showAllEmployees();
    List<Object[]> showAndOrderBySurname();
    List<Object[]> showEmployeesByName(@Param("name") String name);
    List<Object[]> showEmployeeById(@Param("id") int id);
    List<Object[]> showEmployeesBySurname(@Param("surname") String surname);
    List<Object[]> showEmployeesByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
    List<String> showActionsOfThisEmployee(@Param("login") String login);

}
