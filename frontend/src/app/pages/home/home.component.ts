import { Component } from '@angular/core';
import { LandingComponent } from '../../components/landing/landing.component';
import { TableComponent } from '../../components/table/table.component';
import { CardsComponent } from '../../components/cards/cards.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [LandingComponent, TableComponent, CardsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.less',
})
export class HomeComponent {}
