import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { UsuarioTypeEnum } from '../../models/usuario/usuarioTypeEnum';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.component.html'
})
export class HeaderComponent {
  usuarioTypeEnums = UsuarioTypeEnum;
  isAdminSystem: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;
  isAdminCine: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Cine;
  isUser: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Usuario;

  constructor(private router: Router) { }

  logout() {
    localStorage.removeItem('rol');
    localStorage.removeItem('usuario_Id');
    this.router.navigate(['/login'])
  }
}
