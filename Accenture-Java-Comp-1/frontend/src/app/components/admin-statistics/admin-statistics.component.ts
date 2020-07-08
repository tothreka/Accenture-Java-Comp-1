import {Component, OnInit} from '@angular/core';
import {ReservationListItemModel} from "../../models/reservationListItem.model";
import {ReservationService} from "../../services/reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-statistics',
  templateUrl: './admin-statistics.component.html',
  styleUrls: ['./admin-statistics.component.css']
})
export class AdminStatisticsComponent implements OnInit {
  reservationList: Array<ReservationListItemModel>;

  constructor(private reservationsService: ReservationService, private router: Router) {
  }

  ngOnInit(): void {
    this.fetchReservations();
  }

  fetchReservations() {
    this.reservationsService.fetchAllReservation().subscribe(
      data => {
        this.reservationList = data;
      })
  }

  goBack() {
    this.router.navigate(["/adminMain"]);
  }
}
