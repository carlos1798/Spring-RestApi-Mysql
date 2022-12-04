package com.Springmysql.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoEmpleado extends JpaRepository<Empleado,Long>{
    
  
}
