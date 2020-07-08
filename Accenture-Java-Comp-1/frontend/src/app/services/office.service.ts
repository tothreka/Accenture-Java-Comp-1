import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AdminLoginDataModel} from "../models/adminLoginData.model";
import {EmployeeRegistrationModel} from "../models/employeeRegistration.model";
import {EmployeeLoginDataModel} from "../models/employeeLoginData.model";
import {EmployeeDataModel} from "../models/employeeData.model";

const BASE_URL = "http://localhost:8080/api/staff";

@Injectable({providedIn: 'root'})

export class OfficeService {

  constructor(private https: HttpClient) {
  }


  verifyAdmin(adminData: AdminLoginDataModel): Observable<any> {
    return this.https.post(BASE_URL + "/admin/login", adminData)
  }

  fetchAdminData(id: number): Observable<any> {
    return this.https.get(BASE_URL + "/admin/" + id)
  }

  registerEmployee(newEmployeeData: EmployeeRegistrationModel) {
    return this.https.post(BASE_URL + "/register", newEmployeeData);
  }

  /*getEmployeeData(newEmployeeData: EmployeeRegistrationModel) {
    return this.https.get(BASE_URL + "/employee/" + newEmployeeData);
  }*/
  verifyEmployee(employeeLoginData: EmployeeLoginDataModel): Observable<any> {
    return this.https.post(BASE_URL + "/employee/login", employeeLoginData)
  }

  fetchEmployeeData(id: number): Observable<any> {
    return this.https.get(BASE_URL + "/employee/" + id)
  }

  updateEmployeeData(employeeData: EmployeeDataModel, employeeId: number): Observable<any> {
    return this.https.put(BASE_URL + "/employee/update/" + employeeId, employeeData);
  }

  getPosition(employeeId: number) {
    return this.https.get(BASE_URL + "/position/" + employeeId);
  }
}
