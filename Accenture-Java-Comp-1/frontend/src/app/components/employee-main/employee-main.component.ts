import {Component, OnInit} from '@angular/core';
import {EmployeeDataModel} from "../../models/employeeData.model";
import {OfficeService} from "../../services/office.service";
import {FormControl, FormGroup} from "@angular/forms";
import {NewReservationModel} from "../../models/newReservation.model";
import {ReservationService} from "../../services/reservation.service";
import {ActivatedRoute} from "@angular/router";
import {ReservationItemModel} from "../../models/reservationItem.model";

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
  private employeeId: number;

  constructor(private officeService: OfficeService, private reservationService: ReservationService, private activatedRoute: ActivatedRoute) {
    this.newReservationForm = new FormGroup({
      'day': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.employee = JSON.parse(localStorage.getItem('employee'));
    this.employeeId = this.employee.id;
    this.fetchEmployeeData(this.employeeId);
  }

  fetchEmployeeData(id: number) {
    this.officeService.fetchEmployeeData(id).subscribe(
      data => {
        this.employee = data;
      },
      error => {
        console.log(error)
      }
    );
    this.reservationService.fetchReservationsOfCurrentEmployee(this.employeeId).subscribe(
      reservationList => {
        this.employee.reservationList = <Array<ReservationItemModel>>reservationList;
      },
      error => {
        console.log(error)
      }
    )
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

  showChangeIcons() {

  }
}
