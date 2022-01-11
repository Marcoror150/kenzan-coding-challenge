package employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class EmployeeController
{
    /**
     * The class responsible for access to perform operations to the DB containing employees.
     */
    private final EmployeeRepository repository;

    /**
     * The class responsible for wrapping {@link Employee} objects within an entity model.
     */
    private final EmployeeModelAssembler assembler;

    /**
     * Constructor.
     *
     * @param repository The class responsible for access to perform operations to the DB containing employees.
     * @param assembler  The class responsible for wraping {@link Employee} objects within an entity model.
     */
    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler)
    {
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * When the employees link is selected, a list of all employees as entity models is given.
     *
     * @return Every employee wrapped as entity models.
     */
    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all()
    {
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * When a post request is made on the employee link, a new employee will be created and saved into the DB.
     *
     * @param newEmployee The new employee object created from the post request.
     * @return Response link to the newly created employee and information about said employee.
     */
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee)
    {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * Returns all the info of a single employee denoted by the {id} after the employee link.
     *
     * @param id The ID of the employee whose information will be displayed.
     * @return The entity model of the employee.
     */
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id)
    {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    /**
     * When a put request is called for a single employee given their unique ID, their information will be set to the
     * new parameters given from the request.
     *
     * @param newEmployee The new employee object that will replace the current employee whose ID matches the given ID.
     * @param id          The ID of the employee to replace.
     * @return A link to the newly replaced employee.
     */
    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
    {

        Employee updatedEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setMiddleInitial(newEmployee.getMiddleInitial());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setDateOfEmployment(newEmployee.getDateOfEmployment());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * Once authorized, the employee's status will be set to inactive and will not be retrievable from all employees
     * or by searching their unique ID.
     *
     * @param id The ID of the employee to set to inactive status.
     * @return A response entity with no content.
     */
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> setEmployeeToInactive(@PathVariable Long id)
    {
        repository.findById(id).map(employee -> {
                    employee.setStatus(Status.INACTIVE);
                    return repository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return ResponseEntity.ok().build();
    }
}