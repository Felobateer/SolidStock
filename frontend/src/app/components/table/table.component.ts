import { Component, Input } from '@angular/core';
import { LoadingComponent } from '../loading/loading.component';
import { Stock } from '../../services/stocks.service';

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
    'Industry',
    'Symbol',
    'Name',
    'Buy',
    'Sell',
    'High/Low',
    '',
  ];
  @Input() stocks: Stock[] = [];

  constructor() {}

  // this function checks the difference in the stock price change if increased return green if decreased returns red
  // if didn't change then it remains white
  checkChange(diff: number) {
    if (diff > 0) {
      return 'text-success';
    } else if (diff < 0) {
      return 'text-danger';
    } else {
      return '';
    }
  }
}
