import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  logout() {
    if (location.href.includes("admin")) {
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
