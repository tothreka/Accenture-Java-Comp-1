import {Component, OnInit} from '@angular/core';
import {AdminItemDataModel} from "../../models/adminItemData.model";
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeRegistrationModel} from "../../models/employeeRegistration.model";
import {OfficeService} from "../../services/office.service";

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

    )
  }
}
