import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './enviroment';

export interface Stock {
  id: number;
  name: string;
  logo: string;
  finnhubIndustry: string;
  symbol: string;
  high: number;
  low: number;
  buy: number;
  sell: number;
  buyChange: number;
  sellChange: number;
}
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
}
