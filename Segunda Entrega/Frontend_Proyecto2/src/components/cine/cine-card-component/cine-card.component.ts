import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CurrencyPipe, DatePipe } from "@angular/common";
import { RouterLink } from "@angular/router";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UserService } from "../../../services/user/user.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { CineResponse } from "../../../models/cine/cineResponse";

@Component({
  selector: 'app-cine-card',
  imports: [RouterLink, DatePipe],
  templateUrl: './cine-card.component.html',
})
export class CineCardComponent {
  usarioTypeEnums = UsuarioTypeEnum;
  @Input({ required: true })
  selectedCine!: CineResponse;

  @Output()
  cineSelected = new EventEmitter<CineResponse>();

  isAdmin: boolean;

  constructor(private userService: UserService, private roleGuardService: RoleGuardService) {
    this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usarioTypeEnums.Administrador_Sistema]);
  }

  deleteAction(): void {
    this.cineSelected.emit(this.selectedCine);
  }
}