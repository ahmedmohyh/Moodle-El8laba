import { User } from './../core/models/user/user';
import { LoginService } from './../core/service/login.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  myform! : FormGroup;
  userName!: FormControl;
  password! : FormControl;
  error!: string;

    constructor(private loginService : LoginService, private router: Router) {
      this.initFormControls();
      this.createForm();
     }

    ngOnInit(): void {
    }

    initFormControls(){
      this.userName = new FormControl('', [Validators.required,]);
      this.password  = new FormControl('',[Validators.required]);

    }

    createForm(){
      this.myform = new FormGroup({
        userName: this.userName,
        password: this.password,
      });
    }

    login(){

      console.log("value of the form", this.myform.value);


      this.loginService.tryLogin(this.myform.value).subscribe({
        next : (data : any) => {
          console.log("The data from the log in" , data);

          //this.course = returnedCourse;
          //console.log("from the data ",data.userName, "the type of the user is", data.userType);
          localStorage.setItem('isAuth','true');
          localStorage.setItem('userId', data.userId)
          if (localStorage.getItem('toBeDirected') != null) {
              this.router.navigate([localStorage.getItem('toBeDirected')]);
              localStorage.removeItem('toBeDirected');
          }

          else
              this.router.navigate(['home'])

      },
      error : (err) => {
        console.log("from the errors", err.error);
        this.error = err.error;
      } ,

      complete :() => {
        console.log('complete has been done!');
        this.error = '';

    }

    });

    }

}
