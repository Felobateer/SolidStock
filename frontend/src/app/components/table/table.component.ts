import { Component } from '@angular/core';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [],
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
  stocks: any[] = [];
}
