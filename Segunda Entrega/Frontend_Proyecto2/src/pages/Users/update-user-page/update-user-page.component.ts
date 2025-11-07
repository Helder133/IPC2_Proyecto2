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
        console.log(error);
      }
    });
  }


}