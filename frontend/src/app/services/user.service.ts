import { Injectable } from '@angular/core';
import { environment } from './enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private api = environment.apiUrl;

  constructor(private http: HttpClient) {}

  createUser(
    name: string,
    email: string,
    password: string,
    username: string
  ): Observable<void> {
    const params = { name, email, password, username };
    return this.http.post<void>(`${this.api}/user/new`, null, { params });
  }

  editUser(id: number, field: string, edit: string): Observable<void> {
    const params = { id: id.toString(), field, edit };
    return this.http.post<void>(`${this.api}/user/edit`, null, { params });
  }

  signIn(username: string, password: string): Observable<any> {
    const params = { username, password };
    return this.http.post<any>(`${this.api}/user/sign-in`, null, { params });
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
