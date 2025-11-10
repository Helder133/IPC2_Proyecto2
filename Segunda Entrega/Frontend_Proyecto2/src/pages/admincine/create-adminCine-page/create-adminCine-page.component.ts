import { Component } from "@angular/core";
import { HeaderComponent } from "../../../components/header/header.component";
import { AdminCineFormComponent } from "../../../components/adminCine/adminCine-form-component/adminCine-form.component";
import { RouterLink } from "@angular/router";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
    selector: 'app-create-adminCine-page',
    imports: [HeaderComponent, AdminCineFormComponent, RouterLink],
    templateUrl: './create-adminCine-page.component.html'
})

export class CreateAdminCinePageComponent {
    usuarioTypeEnums = UsuarioTypeEnum;
    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
}