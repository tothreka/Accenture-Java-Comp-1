import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoleChooserComponent} from "./components/role-chooser/role-chooser.component";
import {EmployeeLoginComponent} from "./components/employee-login/employee-login.component";
import {AdminLoginComponent} from "./components/admin-login/admin-login.component";
import {EmployeeMainComponent} from "./components/employee-main/employee-main.component";


const routes: Routes = [
  {path: "roles", component: RoleChooserComponent},
  {path: "employee-login", component: EmployeeLoginComponent},
  {path: "admin-login", component: AdminLoginComponent},
  {path: "employee-main", component: EmployeeMainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
