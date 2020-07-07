import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable, Subject} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";


const BASE_URL: string = environment.BASE_URL + '/api/users';

@Injectable({providedIn: 'root'})
export class LoginService {
  private loginStatus = new Subject();

  constructor(private http: HttpClient, private router: Router) {
  }

  authenticate(credentials): Observable<any> {

    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.id + ':' + credentials.password),
    } : {});

    return this.http.get(BASE_URL + '/me', {headers: headers});
  }

  getLoginStatus() {
    return this.loginStatus;
  }

  updateLoginStatus() {
    this.loginStatus.next();
  }

  /*logout() {
    this.http.post(BASE_URL + '/logout', {}).subscribe(() => {
      localStorage.removeItem('admin');
      this.router.navigateByUrl('/');
      this.updateLoginStatus();
    });
  }*/

//TODO
  /*getCurrentUser(): LoggedInUserInfo {
    return JSON.parse(localStorage.getItem('user'));
  }*/


}
