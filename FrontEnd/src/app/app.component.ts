import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'moodle-el8laba-app';

constructor(private router: Router){
}

  logOut(){
    console.log("Log out has been called");
    localStorage.removeItem('isAuth');
    this.router.navigate(['login']);
  }

  showLogOutButton() : boolean {

    //console.log(localStorage.getItem('isAuth'));

    if (localStorage.getItem('isAuth') == null)
         return false

    else if (localStorage.getItem('isAuth') == 'true')
    return true;

    else return false;
  }
}
