import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { User } from './user';
import { SignupService } from './signup.service';


@Component({
    selector: 'signup',
    templateUrl: './signup.component.html',
})

export class SignupComponent implements OnInit {
    
    // name:string;
    // password:string;
    user: User;

    constructor(private service: SignupService){
        this.user = { name: '', passwordHash: '' };
    }

    ngOnInit(){

    }

    createUser(){
        let user: User = {name: this.user.name, passwordHash: this.user.passwordHash};
        console.log(user)
        this.service.createUser(user).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        );
        
    }
}