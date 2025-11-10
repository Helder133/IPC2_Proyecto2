import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UsuarioResponse } from '../../../models/usuario/usuarioResponse';
import { ClasificacionResponse } from '../../../models/clasificacion/ClasificacionResponse';

@Component({
  selector: 'app-confirmation-modal-clasificacion',
  imports: [],
  templateUrl: './confirmation-modal-clasificacion.component.html',
})
export class ConfirmationModalClasificacionComponent {

  @Input()
  selectedClasificacion!: ClasificacionResponse;

  @Output()
  confirmationExecuter = new EventEmitter<void>();

  executeConfirmation(): void {
    this.confirmationExecuter.emit();
  }
}
