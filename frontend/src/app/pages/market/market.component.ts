import { Component } from '@angular/core';
import { TableComponent } from '../../components/table/table.component';
import { StocksService } from '../../services/stocks.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { Stock } from '../../models/stock.model';

@Component({
  selector: 'app-market',
  standalone: true,
  imports: [TableComponent, NgbPaginationModule],
  templateUrl: './market.component.html',
  styleUrl: './market.component.less',
})
export class MarketComponent {
  allStocks: Stock[] = [];
  stocks: Stock[] = [];

  constructor(private data: StocksService) {}

  ngOnInit(): void {
    this.importStocks();
  }

  importStocks() {
    this.data.getStocks('stocks/all').subscribe(
      (data: Stock[]) => {
        this.allStocks = data;
        this.stocks = this.allStocks.slice();
        console.log(data);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  searchStock(event: Event): void {
    const inputValue = (event.target as HTMLInputElement).value;
    if (inputValue === '') {
      this.stocks = this.allStocks;
    } else {
      // Filter stocks based on the input value
      this.stocks = this.allStocks.filter((stock) =>
        stock.name.toLowerCase().includes(inputValue)
      );
    }
  }
}
