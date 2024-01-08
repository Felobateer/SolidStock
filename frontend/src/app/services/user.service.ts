import { Injectable } from '@angular/core';
import { environment } from './enviroment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, pipe, tap, catchError, throwError } from 'rxjs';
import { Investor } from '../models/investor.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private api = environment.apiUrl;
  public id: number | null = null;

  constructor(private http: HttpClient) {
    const id = localStorage.getItem('userId');
    if (id) {
      this.id = parseInt(id);
    } else {
      this.id = null;
    }
  }

  getUserById(id: number): Observable<Investor> {
    const url = `${this.api}user/info/${id}`;
    return this.http.get<Investor>(url);
  }

  createUser(
    name: string,
    email: string,
    password: string,
    username: string
  ): Observable<void> {
    const url = `${this.api}user/new/${name}/${email}/${password}/${username}`;
    return this.http.post<void>(url, null).pipe(
      catchError((err: any) => {
        alert(err);
        return throwError('Error failed to create account ' + err.message);
      })
    );
  }

  editUser(id: number, field: string, edit: string): Observable<void> {
    const url = `${this.api}user/edit/${id}/${field}/${edit}`;
    return this.http.post<void>(url, null);
  }

  signIn(username: string, password: string): Observable<any> {
    const url = `${this.api}user/sign-in/${username}`;
    const requestBody = { password }; // Creating an object to send as JSON in the request body

    return this.http.post<any>(url, requestBody).pipe(
      tap((res) => {
        // Save the id to localStorage
        localStorage.setItem('userId', res.id.toString());
        this.id = res.id;
      })
    );
  }

  addBalance(id: number, amount: number): Observable<void> {
    const url = `${this.api}user/add-balance/${id}`;
    const params = new HttpParams().set('amount', amount.toString());
    return this.http.post<void>(url, null, { params });
  }

  deleteAccount(id: number): Observable<string> {
    const url = `${this.api}user/delete-account/${id}`;
    return this.http.delete<string>(url);
  }
}
