import { Component } from '@angular/core';
import { TableComponent } from '../../components/table/table.component';

@Component({
  selector: 'app-market',
  standalone: true,
  imports: [TableComponent],
  templateUrl: './market.component.html',
  styleUrl: './market.component.less',
})
export class MarketComponent {}
