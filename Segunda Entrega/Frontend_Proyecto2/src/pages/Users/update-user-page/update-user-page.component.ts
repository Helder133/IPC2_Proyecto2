import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ActivatedRouteSnapshot, Router, RouterLink } from "@angular/router";
import { UserFormComponent } from "../../../components/users/user-form-component/user-form.component";
import { UserService } from "../../../services/user/user.service";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { HeaderComponent } from "../../../components/header/header.component";

@Component({
  selector: 'app-create-event-page',
  imports: [UserFormComponent, RouterLink, HeaderComponent],
  templateUrl: './update-user-page.component.html',
})
export class UpdateUserPageComponent implements OnInit {
  userCode!: number;
  userToUpdate!: UsuarioResponse;
  exists: boolean = false;
  exception: boolean = false;
  mensajeError!: string;

  constructor(private router: ActivatedRoute, private userService: UserService) {

  }

  ngOnInit(): void {
    this.userCode = this.router.snapshot.params['code'];
    this.userService.getUserByCodeNumber(this.userCode).subscribe({
      next: (userToUpdate) => {
        this.userToUpdate = userToUpdate;
        this.exists = true;
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
    });
  }


}