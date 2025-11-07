import { Component, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";
import { HeaderComponent } from "../../../components/header/header.component";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { UserService } from "../../../services/user/user.service";
import { UserCardComponent } from "../../../components/users/user-card-component/user-card.component";
import { ConfirmationModalComponent } from "../../../components/confirmation-modal/confirmation-modal.component/confirmation-modal.component";

@Component({
    selector: 'app-users-page',
    imports: [HeaderComponent, UserCardComponent, RouterLink, ConfirmationModalComponent],
    templateUrl: './users-page.component.html',
})

export class UsersPageComponent implements OnInit {

    usuarioTypeEnums = UsuarioTypeEnum;
    protected users: UsuarioResponse[] = [];
    selectedUser!: UsuarioResponse;
    deleted: boolean = false;
    isAdmin: boolean;

    exception: boolean = false;
    mensajeError: string = "";

    constructor(private userService: UserService, private roleGuardService: RoleGuardService) {
        this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usuarioTypeEnums.Administrador_Sistema]);
    }
    ngOnInit(): void {
        this.loadUsers();
    }

    onSelectedUser(user: UsuarioResponse): void {
        this.selectedUser = user;
        this.deleted = false;
    }

    private loadUsers(): void {
        this.userService.getAllUsers().subscribe({
            next: (usersFromServer: UsuarioResponse[]) => {
                this.users = usersFromServer;
            }, error: (error: any) => {
                this.exception = true;
                // Si el backend envía un JSON con el campo "error"
                if (error.error && error.error.error) {
                    this.mensajeError = error.error.error;
                }
                // Si no viene en formato esperado, mostrar el mensaje general
                else {
                    this.mensajeError = "Ocurrió un error inesperado. Intente de nuevo.";
                }
            }
        });
    }

    deleteUser(): void {
        this.userService.deleteUser(this.selectedUser.usuario_Id).subscribe({
            next: () => {
                this.loadUsers();
                this.deleted = true;
            },
            error: (error: any) => {
                this.exception = true;
                // Si el backend envía un JSON con el campo "error"
                if (error.error && error.error.error) {
                    this.mensajeError = error.error.error;
                }
                // Si no viene en formato esperado, mostrar el mensaje general
                else {
                    this.mensajeError = "Ocurrió un error inesperado. Intente de nuevo.";
                }
            }
        })
    }
}