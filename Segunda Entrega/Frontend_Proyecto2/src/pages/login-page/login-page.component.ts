import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RequestLogin } from '../../models/login/requestlogin';
import { NgIf } from '@angular/common';
import { loginService } from '../../services/login/login.service';
import { UsuarioResponse } from '../../models/usuario/usuarioResponse';
import { Router, RouterLink } from "@angular/router";

@Component({
    selector: 'app-login-page',
    imports: [FormsModule, ReactiveFormsModule, NgIf, RouterLink],
    templateUrl: './login-page.component.html'
})

export class LoginPageComponent implements OnInit {
    protected responseUsuario!: UsuarioResponse;
    loginFormGroup!: FormGroup;
    fromLogin!: RequestLogin;
    exception: boolean = false;
    mensajeError: string = '';

    constructor(private formBuilder: FormBuilder, private loginService: loginService, private router: Router) {
    }

    ngOnInit(): void {
        this.loginFormGroup = this.formBuilder.group({
            user: [null, [Validators.required, Validators.maxLength(200)]],
            password: [null, [Validators.required, Validators.maxLength(100)]]
        });
    }

    submit(): void {
        this.exception = false;
        console.log('se hizo submit');
        this.saveNewLogin();
    }

    private saveNewLogin(): void {
        this.fromLogin = this.loginFormGroup.value;
        this.loginService.loginUser(this.fromLogin).subscribe({
            next: (usuarioResponse: UsuarioResponse) => {
                this.responseUsuario = usuarioResponse;
                localStorage.setItem('rol', this.responseUsuario.usuarioTypeEnum);
                localStorage.setItem('usuario_Id', JSON.stringify(this.responseUsuario.usuario_Id));
                this.router.navigate(['/home']);
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