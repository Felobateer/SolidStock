import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RegisterFormComponent } from '../../components/register-form/register-form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RegisterFormComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.less',
})
export class RegisterComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder, private user: UserService) {
    this.form = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(8)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  handleLogin() {
    this.user.signIn(this.form.value.username, this.form.value.password);
  }

  handleDemo() {
    this.user.createUser(
      'Investor',
      'investor@solid-stock.com',
      '1,000,000$',
      'Investor'
    );
  }
}
