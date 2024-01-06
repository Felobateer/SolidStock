import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Investor } from '../../models/investor.model';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.less',
})
export class ProfileComponent implements OnInit {
  fields: string[] = ['name', 'email', 'username', 'password', 'balance'];
  investor: Investor = {
    id: 0,
    name: 'name',
    email: 'email',
    username: 'username',
    password: 'password',
    balance: 0,
  };
  id: number = 0;
  constructor(private user: UserService) {}

  ngOnInit(): void {
    this.loadUser(this.id);
  }

  loadUser(id: number) {
    this.user.getUserById(id).subscribe((data) => {
      this.investor = data;
    });
  }

  deleteAccount(id: number) {
    this.user.deleteAccount(id).subscribe((data) => {
      console.log(data);
    });
  }

  editAccount(id: number, field: string, edit: string) {
    this.user.editUser(id, field, edit).subscribe((data) => {
      console.log(data);
    });
  }

  addBalance(id: number, amount: number) {
    this.user.addBalance(id, amount).subscribe((data) => {
      console.log(data);
    });
  }

  mapInvestor(field: string) {
    switch (field) {
      case 'name':
        return this.investor.name;
      case 'email':
        return this.investor.email;
      case 'username':
        return this.investor.username;
      case 'password':
        return this.investor.password;
      case 'balance':
        return this.investor.balance;
      default:
        return null;
    }
  }

  CFL(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }
}
