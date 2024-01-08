import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { UserService } from '../../services/user.service';
import { RegisterFormComponent } from '../../components/register-form/register-form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RegisterFormComponent, ReactiveFormsModule],
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
    this.user
      .signIn(this.form.value.username, this.form.value.password)
      .subscribe(
        () => {
          alert('Sign in Successful');
        },
        (error: any) => {
          alert('Sign in unsuccessful');
        }
      );
  }

  handleDemo() {
    this.user.signIn('Investor', '1,000,000$').subscribe(
      () => {
        alert('sign in complete');
      },
      (error: any) => {
        alert('error: ' + error.message);
      }
    );
  }
}
