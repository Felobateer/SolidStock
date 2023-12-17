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
}
@Injectable({
  providedIn: 'root',
})
export class StocksService {
  private api = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getStocks(type: string): Observable<Stock[]> {
    // const stocks: Stock[] = [];
    // this.http.get(this.api + type).subscribe(data => {
    //   data.map(stock => stocks.push(
    //     id: stock.id,
    //     name: stock.name,
    //     img: stock.weburl,
    //     industry: stock.finnhubIndustry,
    //     symbol: stock.symbol,
    //     high: stock.high,
    //     low: stock.low,
    //     buy: stock.buy,
    //     sell: stock.sell,
    //   ))
    // })
    return this.http.get<Stock[]>(this.api + type);
  }
}
