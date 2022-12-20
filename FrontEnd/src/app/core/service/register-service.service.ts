import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {

  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient){}


  public tryRegister(user: User): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}/register`,user);
  }
}
