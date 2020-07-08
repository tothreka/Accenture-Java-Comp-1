import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  adminLoggedIn: boolean = false;


  constructor(private router: Router) {
  }

  ngOnInit(): void {
    if (location.href.includes("admin")) {
      this.adminLoggedIn = true;
    }
  }

  logout() {
    if (location.href.includes("admin") || location.href.includes("reservations")) {
      localStorage.removeItem('admin');
      this.router.navigate(['']);
      console.log("Logged out az admin");
    } else if (location.href.includes("employee")) {
      localStorage.removeItem('employee');
      this.router.navigate(['']);
      console.log("Logged out az employee");
    } else {
      console.log("You are not logged is.")
    }
  }
}
