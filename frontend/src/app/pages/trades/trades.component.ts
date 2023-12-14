import { Component } from '@angular/core';
import { GraphComponent } from '../../components/graph/graph.component';
import { OngoingComponent } from '../../components/ongoing/ongoing.component';

@Component({
  selector: 'app-trades',
  standalone: true,
  imports: [GraphComponent, OngoingComponent],
  templateUrl: './trades.component.html',
  styleUrl: './trades.component.less',
})
export class TradesComponent {}
