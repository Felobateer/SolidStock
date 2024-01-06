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

  constructor(private http: HttpClient) {}

  getUserById(id: number): Observable<Investor> {
    const url = `${this.api}/services/user/info/${id}`;
    return this.http.get<Investor>(url);
  }

  createUser(
    name: string,
    email: string,
    password: string,
    username: string
  ): Observable<void> {
    const url = `${this.api}/services/user/new/${name}/${email}/${password}/${username}`;
    return this.http.post<void>(url, null).pipe(
      catchError((err: any) => {
        alert(err);
        return throwError('Error failed to create account ' + err.message);
      })
    );
  }

  editUser(id: number, field: string, edit: string): Observable<void> {
    const url = `${this.api}/services/user/edit/${id}/${field}/${edit}`;
    return this.http.post<void>(url, null);
  }

  signIn(username: string, password: string): Observable<any> {
    const url = `${this.api}/services/user/sign-in/${username}`;
    const params = new HttpParams().set('password', password);
    return this.http
      .post<any>(url, null, { params })
      .pipe(tap((res) => (this.id = res.id)));
  }

  addBalance(id: number, amount: number): Observable<void> {
    const url = `${this.api}/services/user/add-balance/${id}`;
    const params = new HttpParams().set('amount', amount.toString());
    return this.http.post<void>(url, null, { params });
  }

  deleteAccount(id: number): Observable<string> {
    const url = `${this.api}/services/user/delete-account/${id}`;
    return this.http.delete<string>(url);
  }
}
