import { User } from './../models/user/user';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient, private router: Router,){}

  public tryLogin(user: User): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}/login`,user);
  }



}
