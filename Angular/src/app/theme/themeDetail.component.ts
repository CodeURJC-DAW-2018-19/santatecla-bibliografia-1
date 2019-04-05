import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';
import { TabsService } from "../tabs/tabs.service";
import { Citation, CitationService } from '../citation/citation.service';
import { TdDialogService } from '@covalent/core';

@Component({
    templateUrl: './themeDetail.component.html',
    selector: 'themeDetail',
})
export class ThemeDetailComponent {
    @Input()
    theme: Theme;
    @Input()
    citations:Citation[]
    

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public serviceCit: CitationService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService) { }

        saveCitation(citation: Citation) {
            console.log(citation)
            this.serviceCit.saveCitation(citation).subscribe(
                _ => { },
                (error: Error) => console.error('Error updating a book: ' + error),
            );
            window.history.back();
        }
    
        deleteCitation(citation: Citation) {
            this._dialogService.openConfirm({
                message: '¿Quiéres borrar este libro?',
                title: 'Confirmar',
                width: '500px',
                height: '175px'
            }).afterClosed().subscribe((accept: boolean) => {
                if (accept) {
                    this.serviceCit
                        .deleteCitation(citation)
                        .subscribe((_) => { }, (error) => console.error(error));
                }
                window.history.back();
            });
        }
}
