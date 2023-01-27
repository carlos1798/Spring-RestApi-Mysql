import { Component } from '@angular/core';
import { Employee } from './empleados/model/model.employee';
import { EmployeeRepository } from './empleados/model/employee.repository';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = 'ui';
  constructor(public empleado:Employee,public employeeRepositorio:EmployeeRepository ){
  }

    get employees(): Employee[]{
      return this.employeeRepositorio.getEmployees();
    }

} 
