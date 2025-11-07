import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UsuarioResponse } from '../../../models/usuario/usuarioResponse';

@Component({
  selector: 'app-confirmation-modal',
  imports: [],
  templateUrl: './confirmation-modal.component.html',
})
export class ConfirmationModalComponent {

  @Input()
  selectedUser!: UsuarioResponse;

  @Output()
  confirmationExecuter = new EventEmitter<void>();

  executeConfirmation(): void {
    this.confirmationExecuter.emit();
  }
}
