import {ReservationItemModel} from "./reservationItem.model";

export interface EmployeeDataModel {
  id: number,
  name: string,
  password: string
  reservationList: Array<ReservationItemModel>;
}
