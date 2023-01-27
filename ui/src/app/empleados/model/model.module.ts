import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeRepository } from './employee.repository';
import { RestDataSource } from './rest.datasource';
import { Employee } from './model.employee';



@NgModule({
    imports: [HttpClientModule],
    providers: [EmployeeRepository,RestDataSource,{provide:RestDataSource}],
    exports:[]

})
export class ModelModule{}