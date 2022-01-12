package employee;

/**
 * This class represents an exception to be thrown whenever an employee cannot be found in the DB.
 */
class EmployeeNotFoundException extends RuntimeException
{
    /**
     * Constructor.
     *
     * @param id The ID of the employee who could not be found.
     */
    EmployeeNotFoundException(Long id)
    {
        super("\nCould not find employee " + id);
    }
}