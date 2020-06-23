import {Component, OnInit} from '@angular/core';
import {OfficeService} from "../../services/office.service";
import {ReservationItemModel} from "../../models/reservationItem.model";

@Component({
  selector: 'app-employee-main',
  templateUrl: './employee-main.component.html',
  styleUrls: ['./employee-main.component.css']
})
export class EmployeeMainComponent implements OnInit {

  loggedInEmployeeId: number;
  reservations: Array<ReservationItemModel>;

  constructor(private officeService: OfficeService) {
    //TODO login
    this.loggedInEmployeeId = JSON.parse(localStorage.getItem('employee')).id;
  }

  ngOnInit(): void {
    this.fetchReservations();
  }

  fetchReservations() {
    this.officeService.fetchCurrentReservationsOfEmployee(this.loggedInEmployeeId).subscribe(reservationList =>
        this.reservations = reservationList,
      error => console.log(error));
  }

}
