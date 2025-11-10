import { Component, EventEmitter, Input, Output } from "@angular/core";
import { RouterLink } from "@angular/router";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { ClasificacionResponse } from "../../../models/clasificacion/ClasificacionResponse";
import { ClasificacionService } from "../../../services/clasificacion/clasificacion.service";

@Component({
  selector: 'app-clasificacion-card',
  imports: [RouterLink],
  templateUrl: './clasificacion-card.component.html',
})
export class ClasificacionCardComponent {
  usarioTypeEnums = UsuarioTypeEnum;
  @Input({ required: true })
  selectedClasificacion!: ClasificacionResponse;

  @Output()
  clasificacionSelected = new EventEmitter<ClasificacionResponse>();
  

  isAdmin: boolean;

  constructor(private clasificacionService: ClasificacionService, private roleGuardService: RoleGuardService) {
    this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usarioTypeEnums.Administrador_Sistema]);
  }

  deleteAction(): void {
    this.clasificacionSelected.emit(this.selectedClasificacion);
  }
}