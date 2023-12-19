import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.less',
})
export class RegisterFormComponent {
  register: FormGroup;
  entryFields: string[] = ['name', 'email', 'username', 'password'];

  constructor(private fb: FormBuilder, private user: UserService) {
    this.register = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(8)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  handleCreate() {
    this.user.createUser(
      this.register.value.name,
      this.register.value.email,
      this.register.value.username,
      this.register.value.password
    );
  }
}
