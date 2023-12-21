import { Component, OnInit } from '@angular/core';

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
    this.animations();
  }

  animations() {
    gsap.from('.shade', { opacity: 0, duration: 1.5, ease: 'smooth' }); // Example ease

    gsap.from('.content p', {
      text: {
        value: '',
        speed: 0.1,
        stagger: 0.1,
        ease: 'smooth', // Example ease
      },
    });
  }
}
