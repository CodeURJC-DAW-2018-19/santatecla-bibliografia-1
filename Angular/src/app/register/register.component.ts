import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/register/auth.service';
import { UserInterface } from 'src/app/register/user-interface';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  //Docker corrections
  loginService:any;

  constructor(private authService: AuthService, private router: Router, private location: Location) { }
  private user: UserInterface = {
    name: '',
    password: ''
  };
  public isError = false;
  public msgError = '';
  ngOnInit() { }

  onRegister(form: NgForm): void {
    if (form.valid) {
      this.authService
        .registerUser(this.user.name, this.user.password)
        .subscribe(user => {
          this.authService.setUser(user);
          const token = user.id;
          this.router.navigate(['/user/profile']);
          location.reload();
        },
        res => {
          this.onIsError();
        });
    } else {
      this.onIsError();
    }

  }

  onIsError(): void {
    this.isError = true;
    setTimeout(() => {
      this.isError = false;
    }, 4000);
  }
}