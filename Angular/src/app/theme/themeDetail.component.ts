import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../login/login.service';
import { TabsService } from "../tabs/tabs.service";
import { Citation, CitationService } from '../citation/citation.service';
import { Book, BookService } from '../book/book.service';
import { TdDialogService } from '@covalent/core';
import * as jspdf from 'jspdf';  
import html2canvas from 'html2canvas'; 

@Component({
    templateUrl: './themeDetail.component.html',
    selector: 'themeDetail',
})
export class ThemeDetailComponent{
    @Input()
    theme: Theme;
    @Input()
    citations:Citation[]
    bk: any[];

    constructor(
        private router: Router,
        public activatedRoute: ActivatedRoute,
        public themeService: ThemeService,
        public serviceCit: CitationService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private tabsService: TabsService) { }

        saveCitation(citation: Citation) {
            console.log(citation)
            this.serviceCit.saveCitation(citation).subscribe(
                _ => { },
                (error: Error) => console.error('Error updating a citation: ' + error),
            );
            window.history.back();
        }
        ngOnInit(): void {
            var bk: Book[];
            bk=[];
            this.themeService.getTheme(this.theme.id).subscribe(
                theme => ( theme.books.forEach(
                    book=> {
                        if (typeof book !== 'undefined' && book.citation !== null){
                            try{bk.push(book)
                            }catch{
                                console.log("Can't do push")
                            }
                        }
                        try{
                            if(bk.length>0){
                                bk.forEach(
                                    book=> this.citations.concat(book.citation)
                                )
                            }
                        }catch{
                            console.log("Can't see the lenght")
                        }
                    }
                ))
        );
        }
    
        deleteCitation(citation: Citation) {
            this._dialogService.openConfirm({
                message: '¿Quiéres borrar esta cita?',
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

        public captureScreen()  
          {  
            document.getElementById('destination').textContent = document.getElementById("contentToConvert").innerHTML;
            
            var data = document.getElementById('contentToConvert');  
                html2canvas(data).then(canvas => {  
                  // Few necessary setting options  
                  var imgWidth = 208;   
                  var pageHeight = 295;    
                  var imgHeight = canvas.height * imgWidth / canvas.width;  
                  var heightLeft = imgHeight;  
              
                  const contentDataURL = canvas.toDataURL('image/png')  
                  let pdf = new jspdf('p', 'mm', 'a4'); // A4 size page of PDF  
                  var position = 0;  
                  pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth,imgHeight)  
                  pdf.save(this.theme.name+'.pdf'); // Generated PDF   
                }); 
          } 
}