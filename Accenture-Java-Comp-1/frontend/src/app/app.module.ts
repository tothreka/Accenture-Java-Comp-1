import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RoleChooserComponent} from './components/role-chooser/role-chooser.component';
import {EmployeeLoginComponent} from './components/employee-login/employee-login.component';
import {AdminMainComponent} from './components/admin-main/admin-main.component';
import {EmployeeMainComponent} from './components/employee-main/employee-main.component';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {AdminLoginComponent} from "./components/admin-login/admin-login.component";
import {NewReservationFormComponent} from './components/new-reservation-form/new-reservation-form.component';

@NgModule({
  declarations: [
    AppComponent,
    RoleChooserComponent,
    EmployeeLoginComponent,
    AdminLoginComponent,
    AdminMainComponent,
    EmployeeMainComponent,
    NavBarComponent,
    NewReservationFormComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
