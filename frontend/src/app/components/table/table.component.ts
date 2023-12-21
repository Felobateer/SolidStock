import { Component, Input } from '@angular/core';
import { LoadingComponent } from '../loading/loading.component';
import { Stock } from '../../services/stocks.service';
import { PurchaseFormComponent } from '../purchase-form/purchase-form.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    LoadingComponent,
    PurchaseFormComponent,
    NgbPaginationModule,
    CommonModule,
  ],
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
  page = 1;
  pageSize = 20;

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
