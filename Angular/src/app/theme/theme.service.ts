import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'

export interface Theme {
  id?: number;
  name: string;
}

const URL = '/api/themes';

@Injectable()
export class ThemeService {
  constructor(private http: Http) { }

  getThemes(customURL: string) {
    return this.http.get(URL + customURL, { withCredentials: false })
      .pipe(
        map(response => response.json()),
        catchError(error => this.handleError(error))
    );
  }

  getTheme(id: number | string) {
    return this.http.get(URL + "/" +id, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  searchTheme(name:string){
    return this.http.get(URL + "?name=" + name, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error theme (' + error.status + '): ' + error.text());
  }
}
