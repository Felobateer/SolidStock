import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faBell, faGear } from '@fortawesome/free-solid-svg-icons';

library.add(faBell, faGear);
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, FontAwesomeModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.less',
})
export class NavbarComponent {
  links: string[] = ['home', 'market', 'trades', 'register'];
  bell = faBell;
  gear = faGear;

  // Capitalize the First Letter
  CFL(word: string): string {
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
  }
}
