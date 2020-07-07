import {Component, OnInit} from '@angular/core';
import {EmployeeDataModel} from "../../models/employeeData.model";
import {OfficeService} from "../../services/office.service";
import {FormControl, FormGroup} from "@angular/forms";
import {NewReservationModel} from "../../models/newReservation.model";
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-employee-main',
  templateUrl: './employee-main.component.html',
  styleUrls: ['./employee-main.component.css']
})
export class EmployeeMainComponent implements OnInit {
  employee: EmployeeDataModel;
  showReservationForm: boolean = false;
  newReservationForm: FormGroup;
  newReservation: NewReservationModel;

  constructor(private officeService: OfficeService, private reservationService: ReservationService) {
    this.newReservationForm = new FormGroup({
      'day': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.employee = JSON.parse(localStorage.getItem('employee'));
  }

  createNewReservation() {
    this.newReservation = {
      'day': this.newReservationForm.get('day').value,
      'person': this.employee.id
    };
    this.reservationService.makeReservation(this.newReservation).subscribe(
      reservationItem => {
        this.employee.reservationList.push(reservationItem);
      }
    )
  }
}
