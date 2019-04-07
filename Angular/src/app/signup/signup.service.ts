import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { User } from './user';

@Injectable()
export class SignupService {

    user: User;

    constructor(private http: HttpClient) { }

    createUser(user:User) {
        const body = JSON.stringify(user)
        const headers = new HttpHeaders({
            'Content-Type': 'application/json'
        });
        return this.http.post<User>('/api/signup', body, { withCredentials: true, headers })
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