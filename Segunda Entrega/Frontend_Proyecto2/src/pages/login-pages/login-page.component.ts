import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Login } from '../../models/login/login';
import { NgIf } from '@angular/common';
import { loginService } from '../../services/login/login.service';
import { RequestLogin } from '../../models/login/requestLogin';

@Component({
    selector: 'app-login-page',
    imports: [FormsModule, ReactiveFormsModule, NgIf],
    templateUrl: './login-page.component.html'
})

export class LoginPageComponent implements OnInit {

    protected requestLogin!: RequestLogin;
    loginFormGroup!: FormGroup;
    newLogin!: Login;
    operationDone: boolean = false;
    mensajeError: string = '';

    constructor(private formBuilder: FormBuilder, private loginService: loginService) {
    }

    ngOnInit(): void {
        this.loginFormGroup = this.formBuilder.group({
            user: [null, [Validators.required, Validators.maxLength(200)]],
            password: [null, [Validators.required, Validators.maxLength(100)]]
        });
    }

    /*login(role: string): void {
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
    }*/

    submit(): void {
        this.operationDone = false;
        console.log('se hizo submit');
        this.saveNewLogin();
    }

    private saveNewLogin(): void {
        this.newLogin = this.loginFormGroup.value as Login;
        this.loginService.loginUser(this.newLogin).subscribe({
            next: (usuarioLogin: RequestLogin) => {
                this.requestLogin = usuarioLogin;
                console.log(this.requestLogin);


                const rolUsuario = JSON.stringify(usuarioLogin.Rol);
                localStorage.setItem('role', rolUsuario);
                console.log(rolUsuario);
                /*
                const userRole = usuarioLogin.Rol;
                console.log('Rol del usuario: ' + userRole);
                localStorage.setItem('role', userRole);
                console.log(localStorage.getItem('role'));
                */
            },
            error: (error: any) => {
                this.operationDone = true;

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