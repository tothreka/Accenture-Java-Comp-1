import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeDataModel} from "../../models/employeeData.model";
import {NewReservationModel} from "../../models/newReservation.model";
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-new-reservation-form',
  templateUrl: './new-reservation-form.component.html',
  styleUrls: ['./new-reservation-form.component.css']
})
export class NewReservationFormComponent implements OnInit {
  employee: EmployeeDataModel;
  showReservationForm: boolean = false;
  newReservationForm: FormGroup;
  newReservation: NewReservationModel;
  @Output() createReservation = new EventEmitter();
  private employeeId: number;

  constructor(private reservationService: ReservationService) {
    this.newReservationForm = new FormGroup({
      'day': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.employee = JSON.parse(localStorage.getItem('employee'));
    this.employeeId = this.employee.id;
  }

  createNewReservation() {
    this.newReservation = {
      'day': this.newReservationForm.get('day').value,
      'person': this.employee.id
    };
    this.reservationService.checkExistingReservation(this.newReservation).subscribe(
      exists => {
        if (!exists) {
          this.reservationService.makeReservation(this.newReservation).subscribe(
            () => {
              console.log("New reservation created");
              this.showReservationForm = false;
              this.createReservation.emit();
            }
          )
        } else {
          console.log("You already have an open reservation for this day.")
        }
      },
      error => {
        console.log(error)
      })

  }

}
