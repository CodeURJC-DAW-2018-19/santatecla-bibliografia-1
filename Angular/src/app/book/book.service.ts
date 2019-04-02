import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'


export interface Book {
  id?: number;
  title: string;
  publishDate?: Date;
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

  getBooks(customURL: string) {
    return this.http.get(URL+customURL, { withCredentials: false })
      .pipe(
        map(response => response.json()),
        catchError(error => this.handleError(error))
    );
  }

  getBook(id: number | string) {
    return this.http.get(URL + "/" +id, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  searchBook(name:string){
    return this.http.get(URL + "?title=" + name, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error book (' + error.status + '): ' + error.text());
  }
}

