import { Component, OnInit } from '@angular/core';
import gsap from 'gsap';
import { TextPlugin } from 'gsap/TextPlugin';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [],
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.less',
})
export class LandingComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    gsap.registerPlugin(TextPlugin);
    this.animations();
  }

  animations() {
    gsap.from('.shade', { opacity: 0, duration: 1, ease: 'power2.inOut' }); // Example ease

    gsap.from('.content p', {
      text: {
        value: '',
        speed: 0.2,
      },
    });
  }
}
