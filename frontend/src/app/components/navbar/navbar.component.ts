import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faBell, faGear } from '@fortawesome/free-solid-svg-icons';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

library.add(faBell, faGear);
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, FontAwesomeModule, NgbDropdownModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.less',
})
export class NavbarComponent {
  links: string[] = ['home', 'market', 'trades', 'register'];
  dropdownMenu: string[] = ['profile', 'premium', 'logout'];
  bell = faBell;
  gear = faGear;
  settings: boolean = false;
  // Capitalize the First Letter
  CFL(word: string): string {
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
  }

  toggleSettings(): void {
    this.settings = !this.settings;
    console.log(this.settings);
  }
}
