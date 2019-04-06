import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Theme } from '../theme/theme.service';
import { Author } from '../author/author.service';
import { Citation } from '../citation/citation.service';
import { RequestOptions } from '@angular/http';


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
  citation?:Citation[];
}

const URL = '/api/books';

@Injectable()
export class BookService {
  constructor(private http: HttpClient) { }

  getBooks(customURL: string) {
    return this.http.get<Book[]>(URL+customURL, { withCredentials: false })
      .pipe(
        map(response => response),
        catchError(error => this.handleError(error))
    );
  }

  getBook(id: number | string) {
    return this.http.get<Book>(URL + "/" +id, { withCredentials: true })
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
  }

  deleteBook(book: Book) {
    const headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });


    return this.http.delete(URL + "/" + book.id, { withCredentials: true, headers })
      .pipe(
        map(response => undefined),
        catchError(error => this.handleError(error))
      );
  }

  searchBook(name:string){
    return this.http.get<Book[]>(URL + "?title=" + name, { withCredentials: true })
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
  }

  saveBook (book:Book){
    const body= JSON.stringify(book)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    if(!book.id){
      return this.http.post<Book>(URL + "/" ,body, {withCredentials: true, headers})
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
    }
    else{
      return this.http.patch<Book>(URL + "/" +book.id,body, {headers})
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
    }  
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error book (' + error.status + '): ' + error.text());
  }
}

