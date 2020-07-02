import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoleChooserComponent} from "./components/role-chooser/role-chooser.component";
import {EmployeeLoginComponent} from "./components/employee-login/employee-login.component";
import {AdminLoginComponent} from "./components/admin-login/admin-login.component";
import {EmployeeMainComponent} from "./components/employee-main/employee-main.component";
import {AdminMainComponent} from "./components/admin-main/admin-main.component";


const routes: Routes = [
  {path: '', component: RoleChooserComponent},
  {path: 'employeeLogin', component: EmployeeLoginComponent},
  {path: 'adminLogin', component: AdminLoginComponent},
  {path: 'employeeMain', component: EmployeeMainComponent},
  {path: 'adminMain', component: AdminMainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
