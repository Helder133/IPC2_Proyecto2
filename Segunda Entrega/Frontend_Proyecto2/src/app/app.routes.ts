import { Routes } from '@angular/router';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { CreateUserComponent } from '../pages/Users/create-user-page/create-user-page.component';
import { UsersPageComponent } from '../pages/Users/users-page/users-page.component';
import { HomePageComponent } from '../pages/home/home-page/home-page.component';
import { UsuarioTypeEnum } from '../models/usuario/usuarioTypeEnum';
import { RoleGuardService } from '../services/security/role-guard.service';
import { UpdateUserPageComponent } from '../pages/Users/update-user-page/update-user-page.component';
import { CinesPageComponent } from '../pages/cine/cines-page/cines-page.component';
import { CreateCinePageComponent } from '../pages/cine/create-cine-page/create-cine-page.component';
import { UpdateCinePageComponent } from '../pages/cine/update-cine-page/update-cine-page.component';
import { AdminCinePageComponent } from '../pages/admincine/adminCine-page/adminCine-page.component';
import { CreateAdminCinePageComponent } from '../pages/admincine/create-adminCine-page/create-adminCine-page.component';
import { ClasificacionPageComponent } from '../pages/clasificacion/clasificacion-page/clasificacion-page.component';
import { UpdateClasificacionPageComponent } from '../pages/clasificacion/update-clasificacion-page/update-clasificacion-page.component';
import { CreateClasificacionComponent } from '../pages/clasificacion/create-clasificacion-page/create-clasificacion-page.component';

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
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'users/update/:code',
        component: UpdateUserPageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'home',
        component: HomePageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema, UsuarioTypeEnum.Administrador_Cine, UsuarioTypeEnum.Usuario, UsuarioTypeEnum.Anunciante] }
    },{
        path: 'cines',
        component: CinesPageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema, UsuarioTypeEnum.Usuario] }
    },
    {
        path: 'cines/new',
        component: CreateCinePageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'cines/update/:code',
        component: UpdateCinePageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    }, 
    {
        path: 'admincines',
        component: AdminCinePageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'admincines/new',
        component: CreateAdminCinePageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'clasificaciones',
        component: ClasificacionPageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'clasificaciones/new',
        component: CreateClasificacionComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    },
    {
        path: 'clasificaciones/update/:code',
        component: UpdateClasificacionPageComponent,
        canActivate: [RoleGuardService],
        data: { allowedRoles: [UsuarioTypeEnum.Administrador_Sistema] }
    }
];
