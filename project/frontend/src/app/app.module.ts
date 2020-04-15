import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {TableModule} from 'primeng/table';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { ProfessorFormularyComponent } from './user-formulary/professor-formulary.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserTableComponent } from './user-table/user-table.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorFormularyComponent,
    UserTableComponent
  ],
  imports: [
    BrowserModule,
    InputTextModule,
    ButtonModule,
    TableModule,
    FormsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
