import { Component } from "@angular/core";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { RouterLink } from "@angular/router";
import { HeaderComponent } from "../../../components/header/header.component";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
    selector: 'app-home-page',
    imports: [ HeaderComponent],
    templateUrl: './home-page.component.html',
})

export class HomePageComponent {
    usuarioTypeEnums = UsuarioTypeEnum;
    isAdminSystem: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
    isAdminCine: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Cine;
    isUser: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Usuario;
    
    usuarioRegistrado!: String;
    constructor(private roleGuardService: RoleGuardService) {
        if (this.isUser) {
            this.usuarioRegistrado = "Como usuario registrado, puedes explorar y reservar entradas para eventos disponibles en nuestra plataforma. También puedes gestionar tu perfil y ver tu historial de reservas.";
        } else if (this.isAdminCine) {
            this.usuarioRegistrado = "Como administrador de cine, tienes la capacidad de gestionar las funciones relacionadas con la programación de películas, la gestión de salas y la supervisión de las operaciones diarias del cine.";
        } else if (this.isAdminSystem) {
            this.usuarioRegistrado = "Como administrador del sistema, tienes acceso completo para gestionar usuarios, roles y configuraciones del sistema. Puedes supervisar todas las actividades y asegurarte de que la plataforma funcione sin problemas.";
        }
    }
}