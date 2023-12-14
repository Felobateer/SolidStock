import { Component } from '@angular/core';
import { GraphComponent } from '../../components/graph/graph.component';
import { TableComponent } from '../../components/table/table.component';

@Component({
  selector: 'app-trades',
  standalone: true,
  imports: [GraphComponent, TableComponent],
  templateUrl: './trades.component.html',
  styleUrl: './trades.component.less',
})
export class TradesComponent {}
