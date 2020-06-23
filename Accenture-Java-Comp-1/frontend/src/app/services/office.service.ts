import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReservationItemModel} from "../models/reservationItem.model";

const BASE_URL = "http://localhose:8080/api/office";

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

  constructor(private https: HttpClient) {
  }


  fetchCurrentReservationsOfEmployee(employeeId: number): Observable<Array<ReservationItemModel>> {
    return this.https.get<Array<ReservationItemModel>>(`${BASE_URL}/reservations/${employeeId}`)
  }
}
