import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Employee } from './model.employee';

const PROTOCOLO = 'http';
const PUERTO = 8080;

@Injectable()
export class RestDataSource {
  baseUrl: string;
  auth_token?: string;

  constructor(private http: HttpClient) {
    this.baseUrl = `${PROTOCOLO}://${location.hostname}:${PUERTO}/`;
  }
  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl + 'employees/all');
  }
  saveEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl + 'employee',employee);
  }
  updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.baseUrl}employee/${employee.emp_no}`,employee);
  }
  deleteEmployee(id: number): Observable<Employee> {
    return this.http.delete<Employee>(`${this.baseUrl}employee/${id}`);
  }
}