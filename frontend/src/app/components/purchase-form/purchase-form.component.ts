import { Component, Input, inject, TemplateRef } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { StocksService } from '../../services/stocks.service';
import { UserService } from '../../services/user.service';
import { Stock } from '../../models/stock.model';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-purchase-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './purchase-form.component.html',
  styleUrl: './purchase-form.component.less',
})
export class PurchaseFormComponent {
  private modal = inject(NgbModal);
  closeResult = '';
  @Input() stock: Stock | null = null;
  openEntry: FormGroup;

  constructor(
    private data: StocksService,
    private user: UserService,
    private fb: FormBuilder
  ) {
    this.openEntry = this.fb.group({
      type: ['buy', Validators.required],
      assets: [0, Validators.required],
    });
  }

  handleConfirm() {
    const id = this.user.id;
    const symbol = this.stock?.symbol;
    const type = this.openEntry.value.type;
    const assets = this.openEntry.value.assets;

    if (type === 'buy') {
      this.data.openBuy(id!, symbol!, assets);
    } else {
      this.data.openSell(id!, symbol!, assets);
    }
  }

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
}
