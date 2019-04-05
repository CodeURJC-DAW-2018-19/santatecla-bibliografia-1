import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from '../histogram/data';
import { Author, AuthorService } from './author.service';

@Component({
    selector: 'authorForm',
    templateUrl: './authorForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class AuthorFormComponent {

// Timeframe
dateDeath: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);
dateBirth: Date = new Date(new Date().getTime() - 1 * 60 * 60 * 24 * 1000);
//Provisional para poder hacer los cosos de docker
maxToDate: Date;

}