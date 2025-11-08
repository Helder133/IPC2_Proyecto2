import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UsuarioResponse } from '../../../models/usuario/usuarioResponse';

@Component({
  selector: 'app-confirmation-modal-user',
  imports: [],
  templateUrl: './confirmation-modal-user.component.html',
})
export class ConfirmationModalUserComponent {

  @Input()
  selectedUser!: UsuarioResponse;

  @Output()
  confirmationExecuter = new EventEmitter<void>();

  executeConfirmation(): void {
    this.confirmationExecuter.emit();
  }
}
