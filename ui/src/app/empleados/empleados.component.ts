import { Component } from '@angular/core';
import { EmployeeRepository } from './model/employee.repository';
import { Employee } from './model/model.employee';

@Component({
  selector: 'app-empleados',
  templateUrl: './empleados.component.html',
})
export class EmployeeComponent {

  constructor(public empleado:Employee,public employeeRepositorio:EmployeeRepository ){}
}
