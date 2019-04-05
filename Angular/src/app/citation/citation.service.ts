



import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Book } from '../book/book.service';
import { Theme } from '../theme/theme.service';

export interface Citation {
  id?: number;
  text: string;
  textAux: string;
  book: Book;
  theme: Theme;
}

const URL = '/api/citations';

@Injectable()
export class CitationService {
  constructor(private http: Http) { }

  getCitations(customURL: string) {
    return this.http.get(URL + customURL, { withCredentials: false })
      .pipe(
        map(response => response.json()),
        catchError(error => this.handleError(error))
    );
  }

  getCitation(id: number | string) {
    return this.http.get(URL + "/" +id, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  deleteCitation(citation: Citation) {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(URL + "/" + citation.id, options)
      .pipe(
        map(response => undefined),
        catchError(error => this.handleError(error))
      );
  }


  searchCitation(text:string){
    return this.http.get(URL + "?text=" + text, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error citation (' + error.status + '): ' + error.text());
  }
}



