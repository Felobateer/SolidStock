import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  getStock(sym: string): Observable<Stock> {
    return this.http.get<Stock>(this.api + sym);
  }

  getHistory(id: number): Observable<Stock[]> {
    const params = new HttpParams().set('id', id.toString());
    const url = `${this.api}history`;
    return this.http.get<Stock[]>(url, { params });
  }

  openBuy(id: number, symbol: string, assets: number): Observable<void> {
    return this.http.post<void>(
      `${this.api}open/buy/${id}/${symbol}/${assets}`,
      null
    );
  }

  openSell(id: number, symbol: string, assets: number): Observable<void> {
    return this.http.post<void>(
      `${this.api}open/sell/${id}/${symbol}/${assets}`,
      null
    );
  }

  closeBuy(id: number, symbol: string): Observable<number> {
    return this.http.post<number>(`${this.api}close/buy/${id}/${symbol}`, null);
  }

  closeSell(id: number, symbol: string): Observable<number> {
    return this.http.post<number>(
      `${this.api}close/sell/${id}/${symbol}`,
      null
    );
  }
}
