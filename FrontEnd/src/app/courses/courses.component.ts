import { CoursesService } from './../core/service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../core/models/user/course';
import { Router } from '@angular/router';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
})
export class CoursesComponent implements OnInit {
  public enrolledCourses!: Course[];
  public freeCourses!: Course[];

  constructor(private csService: CoursesService, private router: Router) {}

  ngOnInit(): void {
    this.getMyCourses();
    this.getFreeCourses();
  }

  public getMyCourses(): void {

    this.csService.getEnrolledCourses().subscribe({
      next: (data: Course[]) => {
        this.enrolledCourses = data;

        console.log('The data from the enrolled courses are ', data);
      },
      error: (error: any) => {
        console.log('an error occured ', error);
      },
    });
  }

  public getFreeCourses(): void {

    this.csService.getFreeCourses().subscribe({
      next: (data: Course[]) => {
        this.freeCourses = data;

        console.log('The data from the free courses are ', data);
      },
      error: (error: any) => {
        console.log('an error occured ', error);
      },
    });
  }

  public openCourse(courseId: number): void {
    var link = `/courses/${courseId}`;
    console.log("link is", link);

    this.router.navigate([`/courses/${courseId}`]);
  }
  
}
