import { Router } from '@angular/router';
import { RegisterServiceService } from './../core/service/register-service.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Form, FormBuilder, Validators } from '@angular/forms';
import { userNameValidator } from './async.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm! : FormGroup;

  constructor(private fb: FormBuilder, private regService: RegisterServiceService, private router: Router) {
    this.createFormGroup();

    // this.registerForm.valueChanges.subscribe({
    //   next: (data: any) => {
    //     console.log('changes', data);

    //     eroor: (error: any) => {
    //       console.log('error has been logged in the constructor of the regitser');
    //     };
    //   },
    // });
  }

  private createFormGroup (){

    this.registerForm = this.fb.group({
      firstName: ['',[Validators.required]],
      lastName: ['', [Validators.required]],
      mail: ['', [Validators.required]],
      userName: ['',
      {
        validators: [Validators.required],
        asyncValidators: [userNameValidator()],
        updateOn: 'blur'
      }
    ],
      password: ['', [Validators.required]],
      userType:['TEACHER',  [Validators.required]],
      checkAgree: [false, [Validators.requiredTrue]]

  });
}
  ngOnInit(): void {
  }

  tryRegister() : void {

    console.log("value of the form", this.registerForm.value);

    this.regService.tryRegister(this.registerForm.value).subscribe({
      next : (data : any) => {
        //this.course = returnedCourse;
        console.log("from the data ",data.userName, "the type of the user is", data.userType);

        this.router.navigate(['login']);
    },
    error : (err) => {
      console.log("from the errors", err.error);
    } ,

    complete :() => {
      console.log('complete has been done!');
  }

  });

  }
}
