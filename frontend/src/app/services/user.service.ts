import { Injectable } from '@angular/core';
import { environment } from './enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable, pipe, tap, catchError, throwError } from 'rxjs';

export interface Investor {
  id: number;
  name: string;
  email: string;
  password: string;
  username: string;
  balance: number;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private api = environment.apiUrl;
  public id: number | null = null;

  constructor(private http: HttpClient) {}

  getUserById(id: number): Observable<Investor> {
    const url = `${this.api}/user/info/${id}`;
    return this.http.get<Investor>(url);
  }

  createUser(
    name: string,
    email: string,
    password: string,
    username: string
  ): Observable<void> {
    const params = { name, email, password, username };
    return this.http.post<void>(`${this.api}/user/new`, null, { params }).pipe(
      catchError((err: any) => {
        alert(err);
        return throwError('Error failed to create account');
      })
    );
  }

  editUser(id: number, field: string, edit: string): Observable<void> {
    const params = { id: id.toString(), field, edit };
    return this.http.post<void>(`${this.api}/user/edit`, null, { params });
  }

  signIn(username: string, password: string): Observable<any> {
    const params = { username, password };
    return this.http
      .post<any>(`${this.api}/user/sign-in`, null, { params })
      .pipe(tap((res) => (this.id = res.id)));
  }

  addBalance(id: number, amount: number): Observable<void> {
    const params = { id: id.toString(), amount: amount.toString() };
    return this.http.post<void>(`${this.api}/user/add-balance`, null, {
      params,
    });
  }

  deleteAccount(id: number): Observable<string> {
    return this.http.delete<string>(`${this.api}/user/delete-account/${id}`);
  }
}
