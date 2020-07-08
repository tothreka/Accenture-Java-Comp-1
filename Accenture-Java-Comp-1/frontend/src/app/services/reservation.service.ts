import {Injectable} from '@angular/core';
import {NewReservationModel} from "../models/newReservation.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ReservationItemModel} from "../models/reservationItem.model";


const BASE_URL = "http://localhost:8080/api/office";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private https: HttpClient) {
  }

  makeReservation(newReservation: NewReservationModel): Observable<any> {
    return this.https.post(BASE_URL, newReservation);
  }

  fetchReservationsOfCurrentEmployee(id: number) {
    return this.https.get(BASE_URL + "/reservations/" + id);
  }

  enterOffice(reservation: ReservationItemModel, id: number): Observable<any> {
    return this.https.put(BASE_URL + "/enter/" + id, reservation);
  }

  leaveOffice(reservation: ReservationItemModel, id: number) {
    return this.https.put(BASE_URL + "/leave/" + id, reservation);
  }

  updateReservationList(employeeId: number, resList: Array<ReservationItemModel>) {
    return this.https.put(BASE_URL + "/update/" + employeeId, resList);
  }

  checkExistingReservation(newReservation: NewReservationModel) {
    return this.https.post(BASE_URL + "/check/", newReservation);
  }

  fetchReservationsForToday(): Observable<any> {
    return this.https.get(BASE_URL + "/today");
  }
}
