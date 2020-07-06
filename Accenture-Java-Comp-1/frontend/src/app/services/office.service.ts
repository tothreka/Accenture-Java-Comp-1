import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AdminLoginDataModel} from "../models/adminLoginData.model";

const BASE_URL = "http://localhost:8080/api/staff";

@Injectable({providedIn: 'root'})

export class OfficeService {

  constructor(private https: HttpClient) {
  }


  verifyAdmin(adminData: AdminLoginDataModel): Observable<any> {
    return this.https.post(BASE_URL + "/admin/login", adminData)
  }
}
