import { Component, ChangeDetectorRef, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, TdDigitsPipe, TdLayoutManageListComponent, tdRotateAnimation } from '@covalent/core';
import { DatePipe } from '@angular/common';
import { single, multi, pie, times } from 'src/app/histogram/data';
import { Theme, ThemeService } from './theme.service';

@Component({
    selector: 'themeForm',
    templateUrl: './themeForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class ThemeFormComponent {
   


// Timeframe
date: Date = new Date(new Date().getTime() - 2 * 60 * 60 * 24 * 1000);



}