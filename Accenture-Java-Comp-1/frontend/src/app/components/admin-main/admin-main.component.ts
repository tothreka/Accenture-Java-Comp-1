import {Component, OnInit} from '@angular/core';
import {AdminItemDataModel} from "../../models/adminItemData.model";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeRegistrationModel} from "../../models/employeeRegistration.model";
import {OfficeService} from "../../services/office.service";
import {EmployeeDataModel} from "../../models/employeeData.model";

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

  constructor(private officeService: OfficeService) {
    this.employeeRegistrationForm = new FormGroup({
      'name': new FormControl(''),
      'password': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.admin = JSON.parse(localStorage.getItem('admin'));
    console.log("admin id: " + this.admin.id);
    console.log("admin name: " + this.admin.name);
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
    debugger;
    for (let item of this.currentEmployee.reservationList) {
      console.log(item.id);
      console.log(item.day);
      console.log(item.reservationStatus);
    }
  }

  showEmployees() {
    debugger;
    this.employeeList = this.admin.employeeList;
  }
}

