import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UsuarioResponse } from '../../../models/usuario/usuarioResponse';
import { AdminCineResponse } from '../../../models/adminCine/AdminCineResponse';

@Component({
  selector: 'app-confirmation-modal-adminCine',
  imports: [],
  templateUrl: './confirmation-modal-adminCine.component.html',
})
export class ConfirmationModalAdminCineComponent {

  @Input()
  selectedAdminCine!: AdminCineResponse;

  @Output()
  confirmationExecuter = new EventEmitter<void>();

  executeConfirmation(): void {
    this.confirmationExecuter.emit();
  }
}
