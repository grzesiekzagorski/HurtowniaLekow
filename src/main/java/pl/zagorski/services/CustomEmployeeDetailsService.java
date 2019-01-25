package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zagorski.domain.Employee;
import pl.zagorski.repositories.CustomEmployeeDetails;
import pl.zagorski.repositories.EmployeeRepositoryImpl;

import java.util.Optional;

@Service
public class CustomEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String loginEmployee) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.getEmployeeByLogin(loginEmployee);

        optionalEmployee
                    .orElseThrow(() -> new UsernameNotFoundException("loginEmployee"));
        return optionalEmployee.
                map(CustomEmployeeDetails::new).get();

    }
}
