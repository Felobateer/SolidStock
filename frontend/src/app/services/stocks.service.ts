import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './enviroment';
import { Stock } from '../models/stock.model';

@Injectable({
  providedIn: 'root',
})
export class StocksService {
  private api = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getStocks(type: string): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.api + type);
  }

  getHistory(id: number): Observable<Stock[]> {
    return this.http.get<Stock[]>(this.api + 'history/' + id);
  }

  openBuy(id: number, symbol: string, assets: number): Observable<void> {
    const params = { id: id.toString(), symbol, assets: assets.toString() };
    return this.http.post<void>(`${this.api}/open/buy`, null, { params });
  }

  openSell(id: number, symbol: string, assets: number): Observable<void> {
    const params = { id: id.toString(), symbol, assets: assets.toString() };
    return this.http.post<void>(`${this.api}/open/sell`, null, { params });
  }

  closeBuy(id: number, symbol: string): Observable<number> {
    const params = { id: id.toString(), symbol };
    return this.http.post<number>(`${this.api}/close/buy`, null, { params });
  }

  closeSell(id: number, symbol: string): Observable<number> {
    const params = { id: id.toString(), symbol };
    return this.http.post<number>(`${this.api}/close/sell`, null, { params });
  }
}
