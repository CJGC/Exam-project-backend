import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';

import { AppComponent } from './app.component';
import { UserFormularyComponent } from './user-formulary/user-formulary.component';
import { FormsModule } from '@angular/forms';
import { UserTableComponent } from './user-table/user-table.component';

@NgModule({
  declarations: [
    AppComponent,
    UserFormularyComponent,
    UserTableComponent
  ],
  imports: [
    BrowserModule,
    InputTextModule,
    ButtonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
