import { CoursesService } from './../core/service/courses.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../core/models/user/course';


@Component({
  selector: 'app-open-course',
  templateUrl: './open-course.component.html',
  styleUrls: ['./open-course.component.css']
})
export class OpenCourseComponent implements OnInit {

  public currentCourse!: Course;
  public currentUserRegistered!: boolean;
  public dataLoaded!: boolean;
  public userloaded!: boolean;
  public password: String = "";
  courseId!: number;
  error!: string;

  constructor(private router: Router, private courseService: CoursesService,private route: ActivatedRoute) {
    this.Init();
    console.log("password is", this.password);


  }

  ngOnInit(): void {

    this.getCourse();
    this.isUserRegistered();


  }

  public isUserRegistered (): void {

    this.courseService.isUserRegisteredAtCourse(this.courseId).subscribe({
      next: (data:boolean) => {
        this.currentUserRegistered = data;
        this.userloaded = true;
        console.log('The data from the enrolled courses are ', this.currentUserRegistered);
      },
      error: (error: any) => {
        console.log('an error occured ', error);
      },
    });
  }

  public getCourse () {

    this.courseService.getCourse(this.courseId).subscribe({

    next: (data:Course) => {
      this.currentCourse = data;
      this.dataLoaded  = true;
      console.log("The current data from open couse component is ", data);
    },

    error: (error: any) => {
      console.log('an error occured ', error);
      this.router.navigate(["404"]);
    },

  });

  }

  public Init() : void {

    this.route.params.subscribe({

      next: (data:any) => {

        this.courseId = data['courseId'];
        //console.log(this.courseId);

      },

      error: (error: any) => {
        console.log('an error occured ', error);
      },

    });

  }

  public registerCourse(): void {

    this.courseService.registerUserAtCourse(this.courseId, this.password).subscribe({
      next : (data : any) => {
        console.log("The data from the log in" , data);
    },
    error : (err) => {
      console.log("from the errors", err.error);
      this.error = err.error;
    } ,

    complete :() => {
      console.log('complete has been done!');
      this.error = '';
      window.location.reload();
  }
  });

  }

}
