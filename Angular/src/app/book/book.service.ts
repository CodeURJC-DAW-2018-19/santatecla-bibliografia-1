import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Theme } from '../theme/theme.service';
import { Author } from '../author/author.service';


export interface Book {
  id?: number;
  title: string;
  publishDate?: Date;
  nameEdit?: string;
  urlEdit?: string;
  urlImgCoverPage?: string;
  urlImgEdit?: string;
  imgId?: number;
  theme?:Theme;
  author?:Author;
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

  deleteBook(book: Book) {
    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(URL + "/" + book.id, options)
      .pipe(
        map(response => undefined),
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

  saveBook (book:Book){
    const body= JSON.stringify(book)
    const headers = new Headers({'Content-Type': 'application/json',withCredentials: true});

    if(!book.id){
      return this.http.post(URL + "/" ,body, {headers})
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
    }
    else{
      return this.http.patch(URL + "/" +book.id,body, {headers})
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
    }  
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error book (' + error.status + '): ' + error.text());
  }
}

