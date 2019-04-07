import { Component } from '@angular/core';
import { tdRotateAnimation } from '@covalent/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { Theme, ThemeService } from '../theme/theme.service';

@Component({
    selector: 'themeForm',
    templateUrl: './themeForm.component.html',
    styleUrls: ['../app.component.css'],
    animations: [tdRotateAnimation],
})
export class ThemeFormComponent {

    theme: Theme;
  
    constructor(
        private router: Router, 
        public activatedRoute: ActivatedRoute, 
        public service: ThemeService,
        public loginService: LoginService) 
        {
            this.theme={
                name:'',
                books:null,
            }
            
        }

    saveTheme(theme:Theme) {
        console.log(theme);
        this.service.saveTheme(theme).subscribe(
            _ => {},
            (error: Error) => console.error('Error updating an author: ' + error),
        ); 
        window.history.back();
    }


}