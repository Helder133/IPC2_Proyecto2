import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CurrencyPipe, DatePipe } from "@angular/common";
import { RouterLink } from "@angular/router";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { UserService } from "../../../services/user/user.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
  selector: 'app-user-card',
  imports: [RouterLink],
  templateUrl: './user-card.component.html',
})
export class UserCardComponent {
  usarioTypeEnums = UsuarioTypeEnum;
  @Input({ required: true })
  selectedUser!: UsuarioResponse;

  @Output()
  userSelected = new EventEmitter<UsuarioResponse>();

  isAdmin: boolean;

  constructor(private userService: UserService, private roleGuardService: RoleGuardService) {
    this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usarioTypeEnums.Administrador_Sistema]);
  }

  deleteAction(): void {
    this.userSelected.emit(this.selectedUser);
  }
}