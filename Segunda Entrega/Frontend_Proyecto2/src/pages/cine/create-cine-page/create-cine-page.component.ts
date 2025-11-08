import { Component } from "@angular/core";
import { HeaderComponent } from "../../../components/header/header.component";
import { CineFormComponent } from "../../../components/cine/cine-form-component/cine-form.component";
import { RouterLink } from "@angular/router";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
    selector: 'app-create-cine-page',
    imports: [CineFormComponent, RouterLink, HeaderComponent],
    templateUrl: './create-cine-page.component.html'
})

export class CreateCinePageComponent {
    usuarioTypeEnums = UsuarioTypeEnum;
    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
}