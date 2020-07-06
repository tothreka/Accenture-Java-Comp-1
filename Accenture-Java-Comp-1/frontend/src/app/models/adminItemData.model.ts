import {EmployeeDataModel} from "./employeeData.model";


export interface AdminItemDataModel {
  id: number,
  name: string,
  employeeList: Array<EmployeeDataModel>;
}
