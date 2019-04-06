import { Component, ViewChild, TemplateRef  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { TdDialogService } from '@covalent/core';
import { User } from './user';
import { SignupService } from './signup.service';
import { MatDialog, MatDialogRef } from '@angular/material';


@Component({
    selector: 'signup',
    templateUrl: './signup.component.html',
})

export class SignupComponent {

    @ViewChild('loginDialog') loginDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    user: User;

    constructor(
        public dialog: MatDialog,
        private service: SignupService,
        public loginService: LoginService) {
        this.user = { name: '', passwordHash: '' };
    }

    createUser() {
        let user: User = { name: this.user.name, passwordHash: this.user.passwordHash };
        console.log(user)
        this.service.createUser(user).subscribe(
            _ => this.dialogRef.close(),
            (error: Error) => console.error('Error updating an author: ' + error),
        );
    }

    openLoginDialog() {
        this.dialogRef = this.dialog.open(this.loginDialog, {
            width: '50%',
            height: '50%',
        });
    }
}