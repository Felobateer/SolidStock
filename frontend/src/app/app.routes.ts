import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { MarketComponent } from './pages/market/market.component';
import { TradesComponent } from './pages/trades/trades.component';
import { PremiumComponent } from './pages/premium/premium.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'market', component: MarketComponent },
  { path: 'trades', component: TradesComponent },
  { path: 'premium', component: PremiumComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'register', component: RegisterComponent },
  { path: '**', redirectTo: '' }, //  catch any unfound routes and redirect to home page
];
