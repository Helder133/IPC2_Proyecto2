import { Component} from "@angular/core";
import { RouterLink } from "@angular/router";
import { UserFormComponent } from "../../../components/users/user-form-component/user-form.component";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { HeaderComponent } from "../../../components/header/header.component";

@Component({
    selector: 'app-create-user-page',
    imports: [UserFormComponent, RouterLink, HeaderComponent],
    templateUrl: './create-user-page.component.html'
})

export class CreateUserComponent {
    usuarioTypeEnums = UsuarioTypeEnum;
    isHeaderVisible: boolean = localStorage.getItem('rol') != null;
    isAdminSystem: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
}