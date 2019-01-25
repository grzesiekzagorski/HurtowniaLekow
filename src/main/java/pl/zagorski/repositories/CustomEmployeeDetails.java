package pl.zagorski.repositories;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.zagorski.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

public class CustomEmployeeDetails extends Employee implements UserDetails {

    public CustomEmployeeDetails(final Employee employee) {
        super(employee);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> collect = getPositions()
                .stream()
                .map(name -> new SimpleGrantedAuthority("ROLE_" + name.getName()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
