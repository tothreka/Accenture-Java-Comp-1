import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AdminItemDataModel} from "../../models/adminItemData.model";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeRegistrationModel} from "../../models/employeeRegistration.model";
import {OfficeService} from "../../services/office.service";
import {EmployeeDataModel} from "../../models/employeeData.model";
import {ReservationItemModel} from "../../models/reservationItem.model";
import {ReservationService} from "../../services/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit {
  admin: AdminItemDataModel;
  showEmployeeRegistration: boolean = false;
  employeeRegistrationForm: FormGroup;
  newEmployeeData: EmployeeRegistrationModel;
  showReservationsBlock: boolean = false;
  currentEmployee: EmployeeDataModel;
  receivedEmployeeDta: EmployeeDataModel;
  employeeList: Array<EmployeeDataModel>;
  adminId: number;
  showEditPanel: boolean;
  @Output() employeeDataEmitter = new EventEmitter;
  @Input() successfulUpdate: boolean;

  constructor(private officeService: OfficeService, private reservationService: ReservationService, private router: Router) {
    this.employeeRegistrationForm = new FormGroup({
      'name': new FormControl(''),
      'password': new FormControl('')
    });
  }

  /*ngOnChange() {
    if (this.successfulUpdate) {
      this.showEditPanel = false;
    }
  }*/

  ngOnInit(): void {
    this.adminId = JSON.parse(localStorage.getItem('admin')).id;
    this.fetchAdminData(this.adminId);
  }

  fetchAdminData(adminId: number) {
    this.officeService.fetchAdminData(this.adminId).subscribe(
      adminData => {
        this.admin = adminData;
        this.employeeList = this.admin.employeeList;
      }
    );
  }

  registerEmployee() {
    this.newEmployeeData = {
      'name': this.employeeRegistrationForm.get('name').value,
      'password': this.employeeRegistrationForm.get('password').value,
      'adminId': this.admin.id
    };
    this.officeService.registerEmployee(this.newEmployeeData).subscribe(
      employeeData => {
        this.receivedEmployeeDta = <EmployeeDataModel>employeeData;
        this.admin.employeeList.push(<EmployeeDataModel>employeeData);
        console.log("New Employee added: " + this.receivedEmployeeDta.id + " / "
          + this.receivedEmployeeDta.name + " / " +
          this.receivedEmployeeDta.reservationList.length
        )
        ;
        console.log(this.admin.employeeList.length)

      },
      error => {
        console.log(error)
      }
    )
  }

  setCurrentEmployee(employee: EmployeeDataModel) {
    this.currentEmployee = employee;
    this.reservationService.fetchReservationsOfCurrentEmployee(employee.id).subscribe(
      reservationList => {
        this.currentEmployee.reservationList = <Array<ReservationItemModel>>reservationList;
        console.log(this.currentEmployee.reservationList.length);
        for (let item of this.currentEmployee.reservationList) {
          console.log("ID:" + item.id);
          console.log("DAY" + item.day);
          console.log("STATUS" + item.reservationStatus);
        }
      })

  }

  showEmployees() {
    this.employeeList = this.admin.employeeList;
  }

  goToReservations() {
    this.router.navigate(["/reservations"]);
  }
}

