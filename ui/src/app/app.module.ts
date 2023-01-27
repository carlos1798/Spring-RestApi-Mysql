import { NgModule } from '@angular/core';
 import { AppComponent } from './app.component';

import { ModelModule } from './empleados/model/model.module';
import { HttpClientModule } from '@angular/common/http';
import { Employee } from './empleados/model/model.employee';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,ModelModule,HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
