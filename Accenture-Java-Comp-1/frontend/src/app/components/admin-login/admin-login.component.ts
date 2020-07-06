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
    let admin = this.adminLogin.value;
    this.officeService.verifyAdmin(admin).subscribe(
      () => {
        localStorage.setItem('admin', admin);
        this.router.navigate(['/adminMain']);
      },
      error => {
        this.adminLogin.reset();
        console.log(error);
      }
    )
  }


}
