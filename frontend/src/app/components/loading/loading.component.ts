import { Component, OnInit } from '@angular/core';
import gsap from 'gsap';

@Component({
  selector: 'app-loading',
  standalone: true,
  imports: [],
  templateUrl: './loading.component.html',
  styleUrl: './loading.component.less',
})
export class LoadingComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    gsap.to('.loading i', { rotate: 720, duration: 1, repeat: -1 });
  }
}
