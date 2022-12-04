package com.Springmysql.persistencia;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/employees")
@RestController
public class MainController {

  private RepoEmpleado repoEmpleado;
  private EmpleadoModeAssembler assembler;

  MainController(RepoEmpleado repoEmpleado ,EmpleadoModeAssembler assembler) {
    this.repoEmpleado = repoEmpleado;
    this.assembler = assembler;

  }

  @PostMapping(value = "add")
  public @ResponseBody String addEmpleado(@RequestParam int id, @RequestParam String nombre,
      @RequestParam String apellido) {
    Empleado e = new Empleado();
    e.setEmp_no(id);
    e.setFirst_name(nombre);
    e.setLast_name(apellido);
    repoEmpleado.save(e);
    return "Guardado";
  }

  @GetMapping("/all")
  CollectionModel<EntityModel<Empleado>> all() {
    List<EntityModel<Empleado>> empleados = repoEmpleado.findAll().stream().map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(empleados,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MainController.class).all()).withSelfRel());
  }
@PostMapping("/employees")
ResponseEntity<?> nuevoEmpleado(@RequestBody Empleado newEmployee) {

  EntityModel<Empleado> entityModel = assembler.toModel(repoEmpleado.save(newEmployee));

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
}
  

  /**
  * @param id
  * @return
  */
  @GetMapping("/{id}")
  EntityModel<Empleado> one(@PathVariable Long id) {

    Empleado empleado = repoEmpleado.findById(id) //
      .orElseThrow(() -> new EmployeeNotFoundException(id));

  return assembler.toModel(empleado);
  
  }

  @PutMapping("/{id}")
  ResponseEntity<?> reemplazarEmpleado(@RequestBody Empleado nuevEmpleado, @PathVariable long id) {

    Empleado updatedEmployee = repoEmpleado.findById(id) //
      .map(empleado -> {
        empleado.setFirst_name(nuevEmpleado.getFirst_name());
        empleado.setLast_name(nuevEmpleado.getLast_name());
        return repoEmpleado.save(empleado);
      }) //
      .orElseGet(() -> {
          nuevEmpleado.setEmp_no(id);
        return repoEmpleado.save(nuevEmpleado);
      });

  EntityModel<Empleado> entityModel = assembler.toModel(updatedEmployee);

  return ResponseEntity //
      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      .body(entityModel);
  }

  @DeleteMapping("/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repoEmpleado.deleteById(id);
  }
}
    

        

    
