import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faBars, faBell, faGear } from '@fortawesome/free-solid-svg-icons';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';

library.add(faBell, faGear, faBars);
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, FontAwesomeModule, NgbDropdownModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.less',
})
export class NavbarComponent {
  links: string[] = ['home', 'market', 'trades', 'register'];
  dropdownMenu: string[] = ['profile', 'premium', 'logout'];
  bell = faBell;
  gear = faGear;
  bars = faBars;
  settings: boolean = false;
  notifications: boolean = false;
  mobile: boolean = false;
  // Capitalize the First Letter
  CFL(word: string): string {
    return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
  }

  toggleSettings(): void {
    this.settings = !this.settings;
  }

  toggleNotifications(): void {
    this.notifications = !this.notifications;
  }

  toggleMobile(): void {
    this.mobile = !this.mobile;
  }
}
