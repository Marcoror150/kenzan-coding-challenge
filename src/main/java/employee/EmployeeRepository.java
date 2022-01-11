package employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This class allows access to perform operations to the DB containing employees.
 */
interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
