package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class configures the HTTP security profile, password hasher, and how unauthorized requests are sent back to the
 * user.
 */
@Configuration
@EnableWebSecurity
public class EmployeeWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
{
    /**
     * The class generates the response page when a user tries to perform an operation they are not authorized for.
     */
    @Autowired
    private EmployeeAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * This method configures a global in-memory authentication for the admin user.
     *
     * @param auth The class responsible for building the credentials for the user.
     * @throws Exception If the in-memory allocation for the user fails for any reason.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin"))
                .authorities("ADMIN");
    }

    /**
     * This method configures the security profile for specific HTTP requests.
     *
     * @param http The HTTP security profile used to define security for specific HTTP requests.
     * @throws Exception If the HTTP security profile fails to build for any reason.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employee").permitAll()
                .antMatchers(HttpMethod.GET, "/employee/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/employee/*").hasAuthority("ADMIN")
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * @return The password encoder that will be used to hash passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}