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
  stocks: any[] = [];
}
