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
    'industry',
    'symbol',
    'name',
    'High/Low',
    'Buy',
    'Sell',
    '',
  ];
  @Input() stocks: Stock[] = [];

  constructor() {}
}
