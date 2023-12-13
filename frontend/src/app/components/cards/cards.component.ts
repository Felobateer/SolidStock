import { Component } from '@angular/core';

@Component({
  selector: 'app-cards',
  standalone: true,
  imports: [],
  templateUrl: './cards.component.html',
  styleUrl: './cards.component.less',
})
export class CardsComponent {
  about: string[] = [
    'Free Service',
    'Global access',
    'Easy to use',
    "Expert's choice",
    'Connect with other Traders',
    'We are here for you',
  ];
}
