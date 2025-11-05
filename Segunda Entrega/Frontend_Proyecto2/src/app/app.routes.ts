import { Routes } from '@angular/router';
import { LoginPageComponent } from '../pages/login-pages/login-page.component';
import { CreateUserComponent } from '../pages/Users/create-user-pages.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPageComponent
    },
    {
        path: 'user/new',
        component: CreateUserComponent
    }
];
