import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from '../histogram/data';
import { Author, AuthorService } from './author.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
    selector: 'authorForm',
    templateUrl: './authorForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorFormComponent {

    author: Author;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public loginService: LoginService) {
            this.author={
                name:'',
            }
        }
    
    saveAuthor(author:Author) {
        console.log(author)
        this.service.saveAuthor(author).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }

}