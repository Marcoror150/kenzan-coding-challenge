package employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * This class populates the employee database with some examples used for testing.
 */
@Configuration
class LoadDatabase
{
    /**
     * Logger used for debugging.
     */
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * This method populates the initial DB with employees used to test the application.
     *
     * @param employeeRepository The class responsible for access to perform operations to the DB containing employees.
     * @return A command line runner that will execute the insertion of the new test employees and log that they have
     * been preloaded.
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository)
    {

        return args -> {
            employeeRepository.save(new Employee("Marc", 'J', "Christensen",
                    LocalDate.of(1996, 6, 18), LocalDate.of(2022, 1, 15)));
            employeeRepository.save(new Employee("Bob", 'A', "Smith",
                    LocalDate.of(1980, 5, 10), LocalDate.of(2019, 7, 6)));
            employeeRepository.save(new Employee("Test", 'C', "Employee",
                    LocalDate.of(1992, 12, 11), LocalDate.of(2018, 4, 1)));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
        };
    }
}