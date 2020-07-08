import {Component, OnInit} from '@angular/core';
import {ReservationItemModel} from "../../models/reservationItem.model";
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-admin-today-numbers',
  templateUrl: './admin-today-numbers.component.html',
  styleUrls: ['./admin-today-numbers.component.css']
})
export class AdminTodayNumbersComponent implements OnInit {
  resForToday: Array<ReservationItemModel>;
  enrolled: number = 0;
  entered: number = 0;
  currentDate: Date;


  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.fetchReservationsForToday();
    this.currentDate = new Date();
  }

  fetchReservationsForToday() {
    this.reservationService.fetchReservationsForToday().subscribe(
      data => {
        //debugger;
        this.resForToday = data;
        this.separateNumbers();
      },
      error => {
        console.log(error)
      }
    )
  }

  separateNumbers() {
    for (let res of this.resForToday) {
      if (res.reservationStatus == "ENROLLED") {
        this.enrolled++;
      }
      if (res.reservationStatus == "ENTERED_OFFICE") {
        this.entered++;
      }
    }
  }

}
