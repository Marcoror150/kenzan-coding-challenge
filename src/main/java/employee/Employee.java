package employee;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents an employee and all their related aspects.
 */
@Entity
class Employee
{
    /**
     * The unique identifier assigned to the employee.
     */
    private @Id
    @GeneratedValue
    Long id;

    /**
     * The first name of the employee.
     */
    private String firstName;

    /**
     * The middle initial of the employee.
     */
    private char middleInitial;

    /**
     * The last name of the employee.
     */
    private String lastName;

    /**
     * The date of birth of the employee.
     */
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;

    /**
     * The starting date of the employee.
     */
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfEmployment;

    /**
     * The current {@link Status} of the employee.
     */
    private Status status;

    /**
     * Default, empty constructor.
     */
    private Employee()
    {
    }

    /**
     * Constructor.
     *
     * @param firstName        The first name of the employee.
     * @param middleInitial    The middle initial of the employee.
     * @param lastName         The last name of the employee.
     * @param dateOfBirth      The date of birth of the employee.
     * @param dateOfEmployment The initial date of employment of the employee.
     */
    public Employee(String firstName, char middleInitial, String lastName, LocalDate dateOfBirth,
                    LocalDate dateOfEmployment)
    {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.status = Status.ACTIVE;
    }

    /**
     * @return The full name of the employee.
     */
    public String getName()
    {
        return this.firstName + " " + this.middleInitial + " " + this.lastName;
    }

    /**
     * @return The unique identifier assigned to the employee.
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * @return The first name of the employee.
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * @return The middle initial of the employee.
     */
    public char getMiddleInitial()
    {
        return this.middleInitial;
    }

    /**
     * @return The last name of the employee.
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Sets the ID of the employee.
     *
     * @param id The new unique identifier of the employee.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName The new first name of the employee.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Sets the middle initial of the employee.
     *
     * @param middleInitial The new middle of the employee.
     */
    public void setMiddleInitial(char middleInitial)
    {
        this.middleInitial = middleInitial;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName The new first name of the employee.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return The date of birth of the employee.
     */
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Update the date of birth of the employee.
     *
     * @param dateOfBirth The new date of birth for the employee.
     */
    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The initial date the employee started.
     */
    public LocalDate getDateOfEmployment()
    {
        return dateOfEmployment;
    }

    /**
     * Updates the initial date the employee started.
     *
     * @param dateOfEmployment The new initial date the employee started.
     */
    public void setDateOfEmployment(LocalDate dateOfEmployment)
    {
        this.dateOfEmployment = dateOfEmployment;
    }

    /**
     * @return The current {@link Status} of the employee.
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Updates the {@link Status} of the employee.
     *
     * @param status The new {@link Status} of the employee.
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * Determines whether the passed object is equal to the calling object based on the unique ID, first name, middle
     * initial, and last name.
     *
     * @param o The other object to compare to.
     * @return Whether the objects are equal to each other.
     */
    @Override
    public boolean equals(Object o)
    {

        if (this == o)
        {
            return true;
        }

        if (!(o instanceof Employee))
        {
            return false;
        }

        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.middleInitial, employee.middleInitial) && Objects.equals(this.lastName,
                employee.lastName);
    }

    /**
     * @return A unique hashcode taking in the unique ID, first name, middle initial, and last name.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.firstName, this.middleInitial, this.lastName);
    }

    /**
     * @return A string representation of the employee containing the unique ID, first name, middle initial, and last
     * name.
     */
    @Override
    public String toString()
    {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + "'" + " middle initial='"
                + this.middleInitial + ", lastName='" + this.lastName
                + "'" + "}";
    }
}
