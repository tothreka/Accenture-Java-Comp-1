import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {OfficeService} from "../../services/office.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit {

  employeeLoginForm: FormGroup;
  showError: boolean = false;
  errorMessage: string;

  constructor(private officeService: OfficeService, private router: Router) {
    this.employeeLoginForm = new FormGroup({
      'id': new FormControl(''),
      'password': new FormControl('')
    });
  }

  ngOnInit(): void {
    this.errorMessage = "Incorrect password or ID. Try again or contact your admin!";
  }


  login() {
    let employeeLoginData = this.employeeLoginForm.value;
    let employeeId = this.employeeLoginForm.get('id').value;

    this.officeService.verifyEmployee(employeeLoginData).subscribe(
      () => {
        this.officeService.fetchEmployeeData(employeeId).subscribe(
          employeeData => {
            localStorage.setItem('employee', JSON.stringify(employeeData));
            this.router.navigate(['/employeeMain'])
          },
          error =>
            console.log(error));
      },
      error => {
        this.employeeLoginForm.reset();
        // debugger;
        this.showError = true;
        console.log(error);
      }
    )
  }
}
