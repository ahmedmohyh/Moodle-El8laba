import { LoginGuard } from './core/guards/login.guard';
import { AuthGuard } from './core/guards/auth.guard';
import { CoursesComponent } from './courses/courses.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { OpenCourseComponent } from './open-course/open-course.component';

const routes: Routes = [
  {path:'login',component: LoginComponent , canActivate:[LoginGuard]},
  {path:'register', component: RegisterComponent, canActivate:[LoginGuard]},
  {path: 'home', component:HomeComponent},
  {path: 'courses', component:CoursesComponent, canActivate:[AuthGuard]},
  {path: 'courses/:courseId', component:OpenCourseComponent, canActivate:[AuthGuard]},
  {path:'', redirectTo:'login', pathMatch:'full'},
  {path:'404', component:NotFoundComponent},
  {path:'**', redirectTo: '404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
