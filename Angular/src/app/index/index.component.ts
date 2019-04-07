import { Component } from '@angular/core';
import { tdRotateAnimation } from '@covalent/core';
import {ActivatedRoute,Router} from '@angular/router'

@Component({
    selector: 'indexComponent',
    templateUrl: './index.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class IndexComponent{
    //Docker corrections
    show:any;
    
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
    ){}



}