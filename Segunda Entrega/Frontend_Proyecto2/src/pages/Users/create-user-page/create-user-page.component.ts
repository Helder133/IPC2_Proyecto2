import { Component} from "@angular/core";
import { RouterLink } from "@angular/router";
import { UserFormComponent } from "../../../components/users/user-form-component/user-form.component";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
    selector: 'app-create-user-page',
    imports: [UserFormComponent, RouterLink],
    templateUrl: './create-user-page.component.html'
})

export class CreateUserComponent {
    usuarioTypeEnums = UsuarioTypeEnum;
    isAdminSystem: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
}