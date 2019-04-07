import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { HttpHeaders } from '@angular/common/http';
import { Book } from '../book/book.service';
import { Theme } from '../theme/theme.service';


@Injectable()
export class ImagesService {

    constructor(private http: Http) { }

    imageAuthor(image, id:number) {
        const formData = new FormData();
        formData.append('file', image);

        return this.http.patch("/api/authors/" + id + "/image", formData, { withCredentials: true })
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
    }

    imageBook(image, id:number) {
        const formData = new FormData();
        formData.append('file', image);

        return this.http.patch("/api/books/" + id + "/image", formData, { withCredentials: true })
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
        );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error author (' + error.status + '): ' + error.text());
    }

}