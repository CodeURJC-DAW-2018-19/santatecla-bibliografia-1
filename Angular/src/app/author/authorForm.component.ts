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
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
    selector: 'authorForm',
    templateUrl: './authorForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorFormComponent {

    author: Author;
    uploadForm: FormGroup;
    formBuilder:FormBuilder;

    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: AuthorService,
        public loginService: LoginService) {
            this.author={
                name:'',
            }
            this.uploadForm = this.formBuilder.group({
                profile: ['']
              });
        }

        
    
    saveAuthor(author:Author) {
        console.log(author)
        const formData = new FormData();
        formData.append('file', this.uploadForm.get('profile').value);
        this.service.saveAuthor(author).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();

        this.service.updateAuthorImage(this.author.id,formData);
    }

    selectEvent(event){
        if (event.target.files.length > 0) {
            const file = event.target.files[0];
            this.uploadForm.get('profile').setValue(file);
          }
    }
}