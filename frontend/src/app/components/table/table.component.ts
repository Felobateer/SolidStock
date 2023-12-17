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
}
