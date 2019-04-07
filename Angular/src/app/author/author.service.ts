import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Book } from '../book/book.service';
import { Theme } from '../theme/theme.service';


export interface Author {
  id?: number;
  name: string;
  urlImage?: string;
  birthDate?: Date;
  deathDate?: Date;
  bornPlace?: string;
  urlMap?: string;
  imgId?: number;
  books?: Book[];
  themes?: Theme[];
  checked?:Boolean;
}

const URL = '/api/authors';


@Injectable()
export class AuthorService {
  constructor(private http: HttpClient) { }

  getAuthors(customURL?: string) {
    if(customURL!=null){
      return this.http.get<Author[]>(URL + customURL, { withCredentials: false })
        .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
        );
    }
    else{
      return this.http.get<Author[]>(URL, { withCredentials: false })
        .pipe(
          map(response => response),
          catchError(error => this.handleError(error))
        );
    }
  }

  getAuthor(id: number | string) {
    return this.http.get<Author>(URL + "/" + id, { withCredentials: true })
      .pipe(
        map(response => response),
        catchError(error => this.handleError(error))
      );
  }

  // postAuthor(url: string, param: any) {
  //   let body = JSON.stringify(param);
  //   return this.http
  //       .post(url, body)
  //       .map(this.extractData)
  //       .catchError(this.handleError);
  //   }

  deleteAuthor(author: Author) {
    const headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });


    return this.http.delete(URL + "/" + author.id, { withCredentials: true, headers })
      .pipe(
        map(response => undefined),
        catchError(error => this.handleError(error))
      );
  }

  searchAuthor(name: string) {
    return this.http.get<Author[]>(URL + "?name=" + name, { withCredentials: true })
      .pipe(
        map(response => response),
        catchError(error => this.handleError(error))
      );
  }

    saveAuthor (author:Author){
      const body= JSON.stringify(author)
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      id:Number;
      if(!author.id){
        return this.http.post<Author>(URL + "/" ,body, {withCredentials: true, headers})
        .pipe(
            map(author => author, //Hace falta guardar el id del nuevo author para hacer luego el updateImage, creo que también habría que llamarlo desde aqui dentro para asegurar que se guarde antes de actualizarlo
            catchError(error => this.handleError(error))
        ));
      }
      else{
        return this.http.patch(URL + "/" +author.id,body,{withCredentials: true, headers})
        .pipe(
            map(author => author,
            catchError(error => this.handleError(error))

        ));
    }
  }

    updateAuthorImage(id:number,formData:FormData){
      this.http.post(URL+"/"+id+"/image", formData).subscribe(
        (res) => console.log(res),
        (err) => console.log(err)
      );
    }

  private extractData(res: Response) {
    let body = res.json();
    return body || {};
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error author (' + error.status + '): ' + error.text());
  }
}
