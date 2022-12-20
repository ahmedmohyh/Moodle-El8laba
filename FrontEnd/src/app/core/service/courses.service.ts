import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/user/course';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

    public getEnrolledCourses(): Observable<Course[]> {

      var userId = localStorage.getItem('userId');
      console.log("The user id is ", userId);

    return this.http.get<Course[]>(`${this.apiServerUrl}/courses/enrolledCourses/${userId}`);
  }

  public getFreeCourses(): Observable<Course[]> {

    var userId = localStorage.getItem('userId');
    console.log("The user id is ", userId);

  return this.http.get<Course[]>(`${this.apiServerUrl}/courses/freeCourses/${userId}`);
}

public isUserRegisteredAtCourse(courseId: number): Observable<boolean> {

  var userId = localStorage.getItem('userId');

  return this.http.get<boolean>(`${this.apiServerUrl}/courses/isRegistered/${courseId}/${userId}`);

}

public getCourse(courseId: number): Observable<Course> {

  return this.http.get<Course>(`${this.apiServerUrl}/courses/${courseId}`);

}

public registerUserAtCourse(courseId: number, password: String) : Observable<any> {

  var userId = localStorage.getItem('userId');
  console.log("user password is ", password);

  return this.http.put<any>(`${this.apiServerUrl}/courses/register/${courseId}/${userId}`,password);

}

}
