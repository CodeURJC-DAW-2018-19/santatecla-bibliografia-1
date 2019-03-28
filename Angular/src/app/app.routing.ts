import { RouterModule } from '@angular/router';

/*import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';*/

import {BookFormComponent} from './book/bookForm.component'
import {AuthorFormComponent} from './author/authorForm.component'
import { AppComponent } from './app.component';
import { ThemeFormComponent } from './theme/themeForm.component';

const appRoutes = [
  /*{ path: 'books', component: BookListComponent, useAsDefault: true },
  { path: 'book/new', component: BookFormComponent },
  { path: 'book/:id', component: BookDetailComponent },*/
  { path: 'book', component: BookFormComponent},
  { path: 'author', component: AuthorFormComponent },
  { path: 'theme', component: ThemeFormComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
