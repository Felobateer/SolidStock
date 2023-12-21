import { Component } from '@angular/core';
import { TableComponent } from '../../components/table/table.component';
import { StocksService, Stock } from '../../services/stocks.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-market',
  standalone: true,
  imports: [TableComponent, NgbPaginationModule],
  templateUrl: './market.component.html',
  styleUrl: './market.component.less',
})
export class MarketComponent {
  stocks: Stock[] = [];

  constructor(private data: StocksService) {}

  ngOnInit(): void {
    this.importStocks();
  }

  importStocks() {
    this.data.getStocks('stocks/all').subscribe(
      (data: Stock[]) => {
        this.stocks = data;
        console.log(data);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  searchStock(event: HTMLInputElement) {
    this.stocks = this.stocks.filter((stock) => stock.name === event.value);
  }
}
