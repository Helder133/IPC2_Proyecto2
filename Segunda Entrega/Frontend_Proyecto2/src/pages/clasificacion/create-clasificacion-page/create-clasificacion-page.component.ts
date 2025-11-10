import { Component} from "@angular/core";
import { RouterLink } from "@angular/router";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { HeaderComponent } from "../../../components/header/header.component";
import { ClasificacionFormComponent } from "../../../components/clasificacion/clasificacion-form-component/clasificacion-form.component";

@Component({
    selector: 'app-create-clasificacion-page',
    imports: [ClasificacionFormComponent, RouterLink, HeaderComponent],
    templateUrl: './create-clasificacion-page.component.html'
})

export class CreateClasificacionComponent {}