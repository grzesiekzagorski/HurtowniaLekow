package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Employee;
import pl.zagorski.repositories.EmployeeDao;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeImpl {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void edit(Employee employee) {
        employeeDao.edit(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findOne(int id) {
        return employeeDao.findOne(id);
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        return employeeDao.getEmployeeByLogin(login);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeDao.getEmployeesByName(name);
    }

    @Override
    public List<Employee> getEmployeesBySurname(String surname) {
        return employeeDao.getEmployeesBySurname(surname);
    }

    @Override
    public List<Employee> getEmployeesByNameAndSurname(String name, String surname) {
        return employeeDao.getEmployeesByNameAndSurname(name,surname);
    }

    @Override
    public List<Object[]> showAllEmployees() {
        return employeeDao.showAllEmployees();
    }

    @Override
    public List<Object[]> showAndOrderBySurname() {
        return employeeDao.showAndOrderBySurname();
    }

    @Override
    public List<Object[]> showEmployeesByName(String name) {
        return employeeDao.showEmployeesByName(name);
    }

    @Override
    public List<Object[]> showEmployeeById(int id) {
        return employeeDao.showEmployeeById(id);
    }

    @Override
    public List<Object[]> showEmployeesBySurname(String surname) {
        return employeeDao.showEmployeesBySurname(surname);
    }

    @Override
    public List<Object[]> showEmployeesByNameAndSurname(String name, String surname) {
        return employeeDao.showEmployeesByNameAndSurname(name,surname);
    }

    @Override
    public List<String> showActionsOfThisEmployee(String login) {
        return employeeDao.showActionsOfThisEmployee(login);
    }
}
