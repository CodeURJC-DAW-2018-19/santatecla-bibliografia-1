import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Theme } from '../theme/theme.service';

export interface Chart {
    themes: Theme[];
    numBooks: number[];
    names?:any;
}

@Injectable()
export class ChartService {
    constructor(private http: HttpClient) { }

    getChart(){
        return this.http.get<Chart>('/api/chart', { withCredentials: false })
        .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
        );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error author (' + error.status + '): ' + error.text());
      }

}