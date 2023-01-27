import { Injectable } from "@angular/core";

Injectable()
export class Employee {
    constructor(
        public emp_no?: number,
        public birth_date?: Date,
        public first_name?: String,
        public last_name?: String,
        public gender?: String,
        public hire_date?: Date,
    ){}

    }