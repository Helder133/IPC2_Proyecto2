import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login-page',
    imports: [],
    templateUrl: './login-page.component.html',
})

export class LoginPageComponent implements OnInit {
    alreadyLoggedIn!: boolean;
    currentRole!: string | null;


    constructor(private router: Router) {

    }

    ngOnInit(): void {
        this.alreadyLoggedIn = localStorage.getItem('role') != null;
        this.currentRole = localStorage.getItem('role');
    }

    login(role: string): void {
        localStorage.setItem('role', role);
        switch (role) {
            case 'ADMINISTRADOR SISTEMA':
                this.router.navigate(['/users']);
                break;
            case 'ADMINISTRADOR CINE':
                this.router.navigate(['/users']);
                break;
            case 'USUARIO':
                this.router.navigate(['/users']);
        }
    }

    logout(): void {
        localStorage.removeItem('role');
        this.ngOnInit();
    }
}