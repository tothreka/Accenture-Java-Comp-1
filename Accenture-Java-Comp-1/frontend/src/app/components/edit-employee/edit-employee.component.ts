import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeDataModel} from "../../models/employeeData.model";
import {OfficeService} from "../../services/office.service";

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  editEmployeeForm: FormGroup;
  @Input() employee: EmployeeDataModel;
  @Output() successfulUpdate = new EventEmitter();

  constructor(private officeService: OfficeService) {
    this.editEmployeeForm = new FormGroup({
      'name': new FormControl(''),
      'password': new FormControl('')
    });
  }

  ngOnInit(): void {
  }

  saveEmployee() {
    let employeeData: EmployeeDataModel;
    employeeData = this.editEmployeeForm.value;
    this.officeService.updateEmployeeData(employeeData, this.employee.id).subscribe(
      () => {
        console.log("employee data updated.");
        this.successfulUpdate.emit(true);
      },
      error => {
        console.log(error);
      }
    )
  }
}
