import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Book } from '../book/book.service';

export interface Theme {
  id?: number;
  name: string;
  books: Book[];
}

const URL = '/api/themes';

@Injectable()
export class ThemeService {
  constructor(private http: HttpClient) { }

  getThemes(customURL: string) {
    return this.http.get<Theme[]>(URL + customURL, { withCredentials: false })
      .pipe(
        map(response => response),
        catchError(error => this.handleError(error))
      );
  }

  getTheme(id: number | string) {
    return this.http.get<Theme>(URL + "/" +id, { withCredentials: true })
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
  }

  deleteTheme(theme: Theme) {
    const headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });


    return this.http.delete(URL + "/" + theme.id, { withCredentials: true, headers })
      .pipe(
        map(response => undefined),
        catchError(error => this.handleError(error))
      );
  }

  saveTheme(theme: Theme) {
    const body= JSON.stringify(theme)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    if(!theme.id){
      return this.http.post<Theme>(URL + "/" ,body, {withCredentials: true, headers})
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
    }
    else{
      return this.http.patch<Theme>(URL + "/" +theme.id,body, {headers})
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
    }  
  }

  searchTheme(name: string) {
    return this.http.get<Theme[]>(URL + "?name=" + name, { withCredentials: true })
      .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
      );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error theme (' + error.status + '): ' + error.text());
  }
}



