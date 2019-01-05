package pl.zagorski.repositories;


import org.springframework.stereotype.Repository;
import pl.zagorski.domain.Character;
import pl.zagorski.domain.Employee;
import pl.zagorski.domain.PurchaseOrder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("EmployeeDao")
public class EmployeeRepositoryImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Employee employee) {
        em.persist(employee);
        em.flush();
    }

    @Override
    public void edit(Employee employee) {
        em.merge(employee);
        em.flush();
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> q = em.createQuery("Select c from Employee c", Employee.class);
        return q.getResultList();
    }

    @Override
    public Employee findOne(int id) {
        Employee employee = em.find(Employee.class,id);
        return employee;
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.login = :login",Employee.class);
        return query.setParameter("login",login).getSingleResult();
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.name = :name",Employee.class);
        return query.setParameter("name",name).getResultList();
    }

    @Override
    public List<Employee> getEmployeesBySurname(String surname) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.surname = :surname",Employee.class);
        return query.setParameter("surname",surname).getResultList();
    }

    @Override
    public List<Employee> getEmployeesByNameAndSurname(String name, String surname) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.name = :name AND e.surname = :surname",Employee.class);
        return query.setParameter("name",name).setParameter("surname",surname).getResultList();
    }

    @Override
    public List<Object[]> showAllEmployees() {
        TypedQuery<Object[]> query = em.createQuery("SELECT e.id,e.name,e.surname,e.login,e.password,p.name FROM Employee e JOIN e.position p",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showAndOrderBySurname() {
        TypedQuery<Object[]> query = em.createQuery("SELECT e.id,e.name,e.surname,e.login,e.password,p.name FROM Employee e JOIN e.position p order by e.surname",Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> showEmployeesByName(String name) {
        TypedQuery<Object[]> query = em.createQuery("SELECT e.id,e.name,e.surname,e.login,e.password,p.name FROM Employee e JOIN e.position p WHERE e.name = :name",Object[].class);
        return query.setParameter("name",name).getResultList();
    }

    @Override
    public List<Object[]> showEmployeesBySurname(String surname) {
        TypedQuery<Object[]> query = em.createQuery("SELECT e.id,e.name,e.surname,e.login,e.password,p.name FROM Employee e JOIN e.position p WHERE e.surname = :surname",Object[].class);
        return query.setParameter("surname",surname).getResultList();
    }

    @Override
    public List<Object[]> showEmployeesByNameAndSurname(String name, String surname) {
        TypedQuery<Object[]> query = em.createQuery("SELECT e.id,e.name,e.surname,e.login,e.password,p.name FROM Employee e JOIN e.position p WHERE e.name = :name AND e.surname = :surname",Object[].class);
        return query.setParameter("name",name).setParameter("surname",surname).getResultList();
    }

    @Override
    public List<String> showActionsOfThisEmployee(String login) {
        TypedQuery<String> query = em.createQuery("SELECT a.name FROM Employee e JOIN e.position p JOIN p.actions a WHERE e.login = :login",String.class);
        return query.setParameter("login",login).getResultList();
    }

}
