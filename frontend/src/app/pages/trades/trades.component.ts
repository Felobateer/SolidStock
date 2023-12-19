import { Component, OnInit } from '@angular/core';
import { GraphComponent } from '../../components/graph/graph.component';
import { TableComponent } from '../../components/table/table.component';
import { Stock, StocksService } from '../../services/stocks.service';

@Component({
  selector: 'app-trades',
  standalone: true,
  imports: [GraphComponent, TableComponent],
  templateUrl: './trades.component.html',
  styleUrl: './trades.component.less',
})
export class TradesComponent implements OnInit {
  id: number = 0;
  stocks: Stock[] = [];

  constructor(private data: StocksService) {}

  ngOnInit(): void {}

  loadHistory(id: number) {
    this.data.getHistory(id).subscribe((data) => {
      this.stocks = data;
    });
  }
}
