package employee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * This class represents and starts the REST employee application.
 */
@SpringBootApplication
public class EmployeeApplication
{
    /**
     * Main method to start the Spring application.
     *
     * @param args The arguments passed from the command line or configuration.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
