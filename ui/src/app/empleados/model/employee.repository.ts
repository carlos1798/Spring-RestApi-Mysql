import { Injectable} from "@angular/core";
import { Observable, } from "rxjs";
import { RestDataSource } from "./rest.datasource";
import { Employee } from "./model.employee";

@Injectable()
export class EmployeeRepository {

    private employees:Employee[] = [];
    private loaded:boolean = false;

    constructor(private dataSource:RestDataSource) {
        dataSource.getEmployees().subscribe(data => {
            this.employees = data});
    }
    loadOrders() {
        this.dataSource.getEmployees().subscribe(employees =>this.employees=employees)
    }
    getEmployees(): Employee[]{
        
        return this.employees
    }
    guardarOrden(employee:Employee):Observable<Employee>{
        return this.dataSource.saveEmployee(employee);

    }
    updateOrden(employee: Employee) {
        this.dataSource.updateEmployee(employee).subscribe(employee => {
            this.employees.splice(this.employees.findIndex(
                o => o.emp_no == employee.emp_no), 1, employee);
        });
    }
    deleteOrder(emp_num: number) {
        this.dataSource.deleteEmployee(emp_num).subscribe(employee => {
            this.employees.splice(this.employees.findIndex(o => emp_num == o.emp_no), 1)
        });
    }

}