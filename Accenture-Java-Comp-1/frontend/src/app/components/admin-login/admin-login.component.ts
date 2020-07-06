import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {OfficeService} from "../../services/office.service";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  adminLogin: FormGroup;

  constructor(private router: Router, private loginService: LoginService, private officeService: OfficeService) {
    //TODO validate
    this.adminLogin = new FormGroup({
      'id': new FormControl(''),
      'password': new FormControl('')
    });
  }

  ngOnInit(): void {
  }

  login() {
    let adminLoginData = this.adminLogin.value;
    let adminId = this.adminLogin.get('id').value;

    this.officeService.verifyAdmin(adminLoginData).subscribe(
      () => {
        this.officeService.fetchAdminData(adminId).subscribe(
          adminData => {
            localStorage.setItem('admin', JSON.stringify(adminData));
            this.router.navigate(['/adminMain'])
          },
          error =>
            console.log(error))
      },
      error => {
        this.adminLogin.reset();
        console.log(error);
      }
    )
  }


}
