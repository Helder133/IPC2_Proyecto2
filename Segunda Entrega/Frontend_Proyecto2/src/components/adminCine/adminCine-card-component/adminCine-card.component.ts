import { Component, EventEmitter, Input, Output } from "@angular/core";
import { RouterLink } from "@angular/router";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UserService } from "../../../services/user/user.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { AdminCineResponse } from "../../../models/adminCine/AdminCineResponse";

@Component({
  selector: 'app-adminCine-card',
  imports: [],
  templateUrl: './adminCine-card.component.html',
})
export class AdminCineCardComponent {
  usarioTypeEnums = UsuarioTypeEnum;
  @Input({ required: true })
  selectedAdminCine!: AdminCineResponse;

  @Output()
  adminCineSelected = new EventEmitter<AdminCineResponse>();

  isAdmin: boolean;

  constructor(private userService: UserService, private roleGuardService: RoleGuardService) {
    this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usarioTypeEnums.Administrador_Sistema]);
  }

  deleteAction(): void {
    this.adminCineSelected.emit(this.selectedAdminCine);
  }
}