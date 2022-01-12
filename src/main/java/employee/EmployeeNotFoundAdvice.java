package employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class sets the response code and provides the user with advice when the passed employee ID is not associated
 * with an employee in the DB.
 */
@ControllerAdvice
class EmployeeNotFoundAdvice
{
    /**
     * Returns the message of the exception to be used to give feedback back to the user.
     *
     * @param ex The exception thrown when an employee is not found.
     * @return The message of the exception.
     */
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex)
    {
        return ex.getMessage();
    }
}
