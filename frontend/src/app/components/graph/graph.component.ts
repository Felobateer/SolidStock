import { Component, Input } from '@angular/core';
import { ChartDataset, ChartOptions } from 'chart.js';
import { StocksService } from '../../services/stocks.service';
import { NgChartsModule } from 'ng2-charts';
import { Stock } from '../../models/stock.model';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-graph',
  standalone: true,
  imports: [NgChartsModule],
  templateUrl: './graph.component.html',
  styleUrl: './graph.component.less',
})
export class GraphComponent {
  @Input() stocks: Stock[] | null = null;
  lineChartData: ChartDataset[] = [{ data: [], label: 'Stock Price' }];

  lineChartLabels: string[] = [
    'last month',
    '2 weeks ago',
    'last week',
    '5 days ago',
    '4 days ago',
    '3 days ago',
    '2 days ago',
    'yesterday',
    'today',
  ];

  lineChartOptions: ChartOptions = {
    responsive: true,
  };

  lineChartLegend = true;
  lineChartType = 'line';
  lineChartPlugins = [];

  constructor(private data: StocksService, private user: UserService) {}

  close(symbol: string, buy: number) {
    const id = this.user.id;
    if (id != null) {
      if (buy > 0) {
        this.data.closeBuy(id, symbol).subscribe((error: any) => {
          alert(`Error closing buy: ${error.message}`);
        });
      } else {
        this.data.closeSell(id, symbol).subscribe((error: any) => {
          alert(`Error closing buy: ${error.message}`);
        });
      }
    }
  }

  select(tracker: string) {
    this.data.getStock(`stocks/${tracker}`).subscribe(
      (res: Stock) => {
        this.lineChartData[0].data = res.prevPrices;
      },
      (error) => {
        alert(`Error fetching stock data: ${error.message}`);
      }
    );
  }

  timeAgo(dateString: Date) {
    const currentDate = new Date();
    const date = new Date(dateString);

    if (isNaN(currentDate.getTime()) || isNaN(date.getTime())) {
      return 'Invalid date';
    }

    const timeDifference = currentDate.getTime() - date.getTime();
    const seconds = Math.floor(timeDifference / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const months = Math.floor(days / 30);
    const years = Math.floor(months / 12);

    if (years > 0) {
      return `${years} ${years === 1 ? 'year' : 'years'} ago`;
    } else if (months > 0) {
      return `${months} ${months === 1 ? 'month' : 'months'} ago`;
    } else if (days > 0) {
      return `${days} ${days === 1 ? 'day' : 'days'} ago`;
    } else if (hours > 0) {
      return `${hours} ${hours === 1 ? 'hour' : 'hours'} ago`;
    } else if (minutes > 0) {
      return `${minutes} ${minutes === 1 ? 'minute' : 'minutes'} ago`;
    } else {
      return `${seconds} ${seconds === 1 ? 'second' : 'seconds'} ago`;
    }
  }
}
