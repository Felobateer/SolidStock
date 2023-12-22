import { Component, inject, TemplateRef } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserService } from '../../services/user.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.less',
})
export class RegisterFormComponent {
  register: FormGroup;
  entryFields: string[] = ['name', 'email', 'username', 'password'];
  registerValues: { [key: string]: string } = {};
  private modal = inject(NgbModal);
  closeResult = '';

  open(content: TemplateRef<any>) {
    this.modal
      .open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
        (result) => {
          this.closeResult = `Closed with: ${result}`;
        },
        (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        }
      );
  }

  private getDismissReason(reason: any): string {
    switch (reason) {
      case ModalDismissReasons.ESC:
        return 'by pressing ESC';
      case ModalDismissReasons.BACKDROP_CLICK:
        return 'by clicking on a backdrop';
      default:
        return `with: ${reason}`;
    }
  }

  constructor(private fb: FormBuilder, private user: UserService) {
    this.register = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(8)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
    this.register.setValue({
      name: this.registerValues['name'] || '',
      email: this.registerValues['email'] || '',
      username: this.registerValues['username'] || '',
      password: this.registerValues['password'] || '',
    });
  }

  handleCreate() {
    const name = this.register.value.name;
    const email = this.register.value.email;
    const username = this.register.value.username;
    const password = this.register.value.password;
    this.user.createUser(name, email, password, username).subscribe(
      () => {
        alert(this.register.value);
      },
      (error) => {
        alert(error);
      }
    );
  }

  CFL(word: string) {
    return word.charAt(0).toUpperCase() + word.slice(1);
  }
}
