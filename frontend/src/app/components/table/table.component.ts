import { Component } from '@angular/core';
import { LoadingComponent } from '../loading/loading.component';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [LoadingComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.less',
})
export class TableComponent {
  theads: string[] = [
    '',
    'industry',
    'symbol',
    'name',
    'High/Low',
    'Buy',
    'Sell',
    '',
  ];
  stocks: stock[] = [];
}
interface stock {
  id: number;
  img: string;
  industry: string;
  symbol: string;
  name: string;
  high: number;
  low: number;
  buy: string;
  sell: string;
}
