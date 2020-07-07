import {Injectable} from '@angular/core';
import {NewReservationModel} from "../models/newReservation.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


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
}
