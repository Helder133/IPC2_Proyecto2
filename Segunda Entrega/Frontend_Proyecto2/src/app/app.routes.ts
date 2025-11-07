import { Routes } from '@angular/router';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { CreateUserComponent } from '../pages/Users/create-user-page/create-user-page.component';
import { UsersPageComponent } from '../pages/Users/users-page/users-page.component';
import { HomePageComponent } from '../pages/home/home-page/home-page.component';
import { UsuarioTypeEnum } from '../models/usuario/usuarioTypeEnum';
import { RoleGuardService } from '../services/security/role-guard.service';
import { UpdateUserPageComponent } from '../pages/Users/update-user-page/update-user-page.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPageComponent
    },
    {
        path: 'users/new',
        component: CreateUserComponent
    },
    {
        path: 'users',
        component: UsersPageComponent,
        canActivate: [RoleGuardService],
        data: {allowedRoles: [UsuarioTypeEnum.Administrador_Sistema]}
    },
    {
        path: 'users/update/:code',
        component: UpdateUserPageComponent,
        canActivate: [RoleGuardService],
        data: {allowedRoles: [UsuarioTypeEnum.Administrador_Sistema]}
    },
    {
        path: 'home',
        component: HomePageComponent,
        canActivate: [RoleGuardService],
        data: {allowedRoles: [UsuarioTypeEnum.Administrador_Sistema, UsuarioTypeEnum.Administrador_Cine, UsuarioTypeEnum.Usuario, UsuarioTypeEnum.Anunciante]}
    }
];
