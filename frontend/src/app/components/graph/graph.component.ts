import { Component } from '@angular/core';
import { ChartDataset, ChartOptions } from 'chart.js';
import { StocksService } from '../../services/stocks.service';
import { NgChartsModule } from 'ng2-charts';

@Component({
  selector: 'app-graph',
  standalone: true,
  imports: [NgChartsModule],
  templateUrl: './graph.component.html',
  styleUrl: './graph.component.less',
})
export class GraphComponent {
  lineChartData: ChartDataset[] = [{ data: [], label: 'Stock Price' }];

  lineChartLabels: string[] = [];

  lineChartOptions: ChartOptions = {
    responsive: true,
  };

  lineChartLegend = true;
  lineChartType = 'line';
  lineChartPlugins = [];

  constructor(private data: StocksService) {}

  loadHistory(id: number) {}
}
