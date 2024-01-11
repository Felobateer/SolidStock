import { Component, OnInit } from '@angular/core';
import { GraphComponent } from '../../components/graph/graph.component';
import { TableComponent } from '../../components/table/table.component';
import { StocksService } from '../../services/stocks.service';
import { Stock } from '../../models/stock.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-trades',
  standalone: true,
  imports: [GraphComponent, TableComponent],
  templateUrl: './trades.component.html',
  styleUrl: './trades.component.less',
})
export class TradesComponent implements OnInit {
  id: number | null;
  stocks: Stock[] = [];

  constructor(private data: StocksService, private user: UserService) {
    this.id = this.user.id;
  }

  ngOnInit(): void {
    this.loadHistory();
  }

  loadHistory() {
    if (this.id) {
      this.data.getHistory(this.id).subscribe(
        (res: Stock[]) => {
          this.stocks = res;
        },
        (error: any) => {
          alert(error.message);
        }
      );
    }
  }
}
