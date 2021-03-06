import {Component, OnInit} from '@angular/core';
import {EmployeeDataModel} from "../../models/employeeData.model";
import {OfficeService} from "../../services/office.service";
import {FormControl, FormGroup} from "@angular/forms";
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
  newReservationForm: FormGroup;
  employeeId: number;
  currentDateFormatted: string;


  //position: number;

  constructor(private officeService: OfficeService, private reservationService: ReservationService, private activatedRoute: ActivatedRoute) {
    this.newReservationForm = new FormGroup({
      'day': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.employee = JSON.parse(localStorage.getItem('employee'));
    this.employeeId = this.employee.id;
    this.fetchEmployeeData(this.employeeId);
    this.getCurrentDate();
    //this.getPosition(this.employeeId);
  }

  getCurrentDate() {
    this.reservationService.getCurrentDate().subscribe(
      date => {
        //debugger;
        this.currentDateFormatted = date.toString();
        console.log(this.currentDateFormatted);
      },
      error => {
        console.log(error)
      }
    )
  }


  setEnterButtonDisabled(res: ReservationItemModel): boolean {
    if (res.day == this.currentDateFormatted) {

      //document.getElementById('enter').setAttribute('disabled', "false");
      return false;
    } else {
      return true;
    }
  }

  setExitButtonDisabled(res: ReservationItemModel): boolean {
    if (res.reservationStatus == "ENTERED_OFFICE") {
      //document.getElementById('exit').setAttribute('disabled', "false");
      return false;
    } else {
      return true;
    }
  }

  /*getPosition(employeeId: number) {
    this.officeService.getPosition(employeeId).subscribe(
      posi => {
        this.position = <number>posi;
      }
    )
  }
*/
  fetchEmployeeData(id: number) {
    this.officeService.fetchEmployeeData(id).subscribe(
      data => {
        this.employee = data;
        localStorage.setItem('employee', JSON.stringify(this.employee));
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

  enterOffice(reservation: ReservationItemModel) {
    reservation.reservationStatus = "ENTERED_OFFICE";
    this.reservationService.enterOffice(reservation, reservation.id).subscribe(
      () => {
        document.getElementById("enter")
          .setAttribute("disabled", "disabled");

      },
      error => {
        console.log(error)
      });
  }

  leaveOffice(reservation: ReservationItemModel) {
    reservation.reservationStatus = "LEFT_OFFICE";
    this.reservationService.leaveOffice(reservation, reservation.id).subscribe(
      () => {

      },
      error => {
        console.log(error);
      });
  }

  isReservationClosed(reservation: ReservationItemModel): boolean {
    let status = reservation.reservationStatus;
    if (status.valueOf() != 'LEFT_OFFICE') {
      return true;
    } else {
      return false;
    }
  }

  update(resList: Array<ReservationItemModel>, employeeId: number) {
    this.reservationService.updateReservationList(employeeId, this.employee.reservationList).subscribe(
      updatedList => {
        this.employee.reservationList = <Array<ReservationItemModel>>updatedList;
        console.log("Reservation list updated");
        location.reload();
      }
    )
  }
}
