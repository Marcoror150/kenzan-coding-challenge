package employee;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * This class handles the conversion of an {@link Employee} object to an entity model.
 */
@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>
{

    /**
     * Wraps an {@link Employee} object into an entity model and returns the result.
     *
     * @param employee The {@link Employee} to convert to an entity model.
     * @return The entity model wrapped employee object.
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee)
    {

        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}