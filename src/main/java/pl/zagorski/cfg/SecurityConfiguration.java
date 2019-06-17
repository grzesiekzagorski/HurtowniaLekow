package pl.zagorski.cfg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.zagorski.repositories.EmployeeRepositoryImpl;
import pl.zagorski.services.CustomEmployeeDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = EmployeeRepositoryImpl.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Autowired
    private CustomEmployeeDetailsService customEmployeeDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customEmployeeDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/warehouse/getRestWarehouse").permitAll()
                .antMatchers("/medicine/allMedicines").hasAnyRole("USER", "ADMIN")
                .antMatchers("/orders/allOrders").hasAnyRole("USER", "ADMIN")
                .antMatchers("/warehouse/allWarehouses").hasAnyRole("USER", "ADMIN")
                .antMatchers("/delivery/allDeliveries").hasAnyRole("ADMIN")
                .antMatchers("/sale/allSales").hasAnyRole("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/delivery/error")
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/medicine/allMedicines")
                .failureUrl("/login-error")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        http.csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/css/**", "/js/**", "/img/**");
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }
}