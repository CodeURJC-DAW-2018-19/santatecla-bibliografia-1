import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule, HttpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HelloComponent } from './hello.component';
import { HeaderComponent} from './header/header.component';

import { BookService } from './book/book.service';
import { AuthorService } from './author/author.service';
import { ThemeService } from './theme/theme.service';
import { LoginService } from './login/login.service';
import { TabsService } from './tabs/tabs.service';

import { TabsComponent } from './tabs/tabs.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from "./register/register.component";
import { AuthorComponent } from './author/author.component';
import { BookComponent } from './book/book.component';
import { ChartComponent } from './chart/chart.component';

import { CovalentBaseEchartsModule } from '@covalent/echarts/base';
import { CovalentBarEchartsModule } from '@covalent/echarts/bar';
import { CovalentTooltipEchartsModule } from '@covalent/echarts/tooltip';

import {
    MatButtonModule,MatListModule, MatIconModule,MatCardModule,MatMenuModule,MatInputModule,
    MatButtonToggleModule, MatProgressSpinnerModule, MatSelectModule, MatSlideToggleModule, 
    MatDialogModule,MatSnackBarModule, MatToolbarModule, MatTabsModule, MatSidenavModule, 
    MatTooltipModule,MatRippleModule,MatRadioModule,MatGridListModule,MatDatepickerModule,
    MatNativeDateModule, MatSliderModule,MatAutocompleteModule,MatCheckboxModule,
} from '@angular/material';

import {
    CovalentCommonModule, CovalentLayoutModule, CovalentMediaModule,CovalentExpansionPanelModule,
    CovalentStepsModule,CovalentLoadingModule,CovalentDialogsModule, CovalentSearchModule,
    CovalentPagingModule,CovalentNotificationsModule,CovalentMenuModule,CovalentDataTableModule, 
    CovalentMessageModule,CovalentVirtualScrollModule
} from '@covalent/core';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';

import { IndexComponent } from './index/index.component';

import { AuthorColumnComponent } from './author/authorColumn.component';
import { AuthorFormComponent } from './author/authorForm.component';
import { AuthorDetailComponent } from './author/authorDetail.component';

import { BookColumnComponent } from './book/bookColumn.component';
import { BookFormComponent } from './book/bookForm.component';
import { BookDetailComponent } from './book/bookDetail.component';

import { ThemeColumnComponent } from './theme/themeColumn.component';
import { ThemeFormComponent } from './theme/themeForm.component';
import { ThemeDetailComponent } from './theme/themeDetail.component';

import { CitationColumnComponent } from './citation/citationColumn.component';
import { CitationService } from './citation/citation.service';
import { CitationFormComponent } from './citation/citationForm.component';

import { routing } from './app.routing';
import { ErrorInterceptor } from './login/error.interceptor';
import { BasicAuthInterceptor } from './login/auth.interceptor';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { ThemeComponent } from './theme/theme.component';
import { registerModuleFactory } from '@angular/core/src/linker/ng_module_factory_loader';


@NgModule({
    imports: [
        BrowserModule, BrowserAnimationsModule,FormsModule,
        RouterModule.forRoot([]),HttpClientModule,JsonpModule,
        /** Material Modules */
        MatButtonModule,MatListModule, MatIconModule, MatCardModule,MatCheckboxModule,
        MatMenuModule, MatInputModule, MatSelectModule, MatButtonToggleModule,
        MatSlideToggleModule,MatProgressSpinnerModule,MatDialogModule, MatSnackBarModule,
        MatToolbarModule, MatTabsModule, MatSidenavModule, MatTooltipModule,MatRippleModule,
        MatRadioModule, MatGridListModule, MatDatepickerModule, MatNativeDateModule,
        MatSliderModule, MatAutocompleteModule,
        /** Covalent Modules */
        CovalentCommonModule, CovalentVirtualScrollModule, CovalentLayoutModule, 
        CovalentMediaModule, CovalentExpansionPanelModule, CovalentStepsModule,
        CovalentDialogsModule, CovalentLoadingModule, CovalentSearchModule,
        CovalentPagingModule,CovalentNotificationsModule, CovalentMenuModule,
        CovalentDataTableModule, CovalentMessageModule,
        // Chart
        
        CovalentBaseEchartsModule, CovalentBarEchartsModule, CovalentTooltipEchartsModule,
        /** Additional **/
        NgxChartsModule,
        routing,
        HttpModule,//Remove when migrated to HttpClient
    ],

    declarations: [AppComponent, HelloComponent, HeaderComponent, LoginComponent, 
        AuthorDetailComponent, BookDetailComponent, ThemeDetailComponent, ChartComponent,
        IndexComponent, AuthorColumnComponent,BookColumnComponent, ThemeColumnComponent,LoginComponent,
        AuthorFormComponent,BookFormComponent, ThemeFormComponent, TabsComponent, AuthorComponent,BookComponent,
        ThemeComponent,CitationColumnComponent,CitationFormComponent, RegisterComponent],

    bootstrap: [AppComponent],
    providers: [BookService, AuthorService, ThemeService, LoginService, TabsService,CitationService,
        { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
})
export class AppModule {
    constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
        matIconRegistry.addSvgIconSet
        (domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
}
