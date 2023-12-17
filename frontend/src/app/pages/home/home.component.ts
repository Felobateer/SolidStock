import { Component, OnInit } from '@angular/core';
import { LandingComponent } from '../../components/landing/landing.component';
import { TableComponent } from '../../components/table/table.component';
import { CardsComponent } from '../../components/cards/cards.component';
import { StocksService, Stock } from '../../services/stocks.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [LandingComponent, TableComponent, CardsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.less',
})
export class HomeComponent implements OnInit {
  stocks: Stock[] = [];

  constructor(private data: StocksService) {}

  ngOnInit(): void {
    this.importStocks();
  }

  importStocks() {
    this.data.getStocks('stocks/ten').subscribe(
      (data: Stock[]) => {
        this.stocks = data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
