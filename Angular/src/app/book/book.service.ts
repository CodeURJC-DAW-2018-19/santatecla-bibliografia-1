import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'


export interface Book {
  id?: number;
  title: string;
  publishDate?: string;
  nameEdit?: string;
  urlEdit?: string;
  urlImgCoverPage?: string;
  urlImgEdit?: string;
  imgId?: number;
}

const URL = '/api/books';

@Injectable()
export class BookService {
  constructor(private http: Http) { }

  getBooks() {
    return this.http.get(URL, { withCredentials: false })
      .pipe(
        map(response => response.json()),
        catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}

