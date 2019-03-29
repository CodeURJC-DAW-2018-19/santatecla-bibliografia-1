import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'

export interface Theme {
  id?: number;
  name: string;
}

const URL = '/api/theme';

@Injectable()
export class ThemeService {
  constructor(private http: Http) { }

  getThemes() {
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