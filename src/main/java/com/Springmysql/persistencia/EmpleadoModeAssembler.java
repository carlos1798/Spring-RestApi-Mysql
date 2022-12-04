package com.Springmysql.persistencia;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoModeAssembler implements RepresentationModelAssembler<Empleado,EntityModel<Empleado>> {

    @Override
    public EntityModel<Empleado> toModel(Empleado empleado) {
         return EntityModel.of(empleado, //
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MainController.class).one(empleado.getEmp_no())).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MainController.class).all()).withRel("empleados"));
  }
    }
    

