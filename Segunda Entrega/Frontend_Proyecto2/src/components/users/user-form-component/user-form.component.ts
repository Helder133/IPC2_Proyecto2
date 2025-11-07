import { Component, Input, OnInit } from "@angular/core";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { UsuarioRequest } from "../../../models/usuario/usuarioRequest";
import { UserService } from "../../../services/user/user.service";
import { KeyValuePipe, NgFor } from "@angular/common";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";

@Component({
    selector: 'app-user-form',
    imports: [NgFor, FormsModule, ReactiveFormsModule, KeyValuePipe],
    templateUrl: './user-form.component.html'
})
export class UserFormComponent implements OnInit {

    @Input()
    isEditMode: boolean = false;
    @Input()
    userToUpdate!: UsuarioResponse;

    newUserForm!: FormGroup;
    newUser!: UsuarioRequest;
    usuarioTypeEnums = UsuarioTypeEnum;
    operationDone: boolean = false;

    exception: boolean = false;
    mensajeError!: string;

    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;

    constructor(private formBuilder: FormBuilder,
        private userService: UserService
    ) {

    }

    ngOnInit(): void {
        this.newUserForm = this.formBuilder.group({
            usuario_Id: [null],
            nombre: [null, [Validators.required, Validators.maxLength(200)]],
            email: [null, [Validators.required, Validators.email, Validators.maxLength(100)]],
            contraseña: [null, [Validators.maxLength(50)]],
            usuarioTypeEnum: [null, [Validators.required]]
        });
        this.reset();
        this.applyPasswordValidators();
    }

    usuarioTypeOptions = Object.keys(UsuarioTypeEnum).filter(val => isNaN(Number(val))).map(key => ({
        key: key,
        value: UsuarioTypeEnum[key as keyof typeof UsuarioTypeEnum]
    }));

    private applyPasswordValidators(): void {
    const passControl = this.newUserForm.get('contraseña');
    if (!passControl) return;

    if (this.isEditMode) {
        // modo edición: contraseña opcional (solo maxLength)
        passControl.setValidators([Validators.maxLength(50)]);
    } else {
        // modo creación: contraseña obligatoria
        passControl.setValidators([Validators.required, Validators.maxLength(50)]);
    }
    passControl.updateValueAndValidity();
}

    submit(): void {
        console.log('se hizo submit');
        if (this.newUserForm.valid) {
            console.log('Formulario válido');
            if (this.isEditMode) {
                this.updateUser();
            } else {
                this.saveNewUser();
            }
        }

    }

    reset(): void {
        this.exception = false;
        this.mensajeError = "";
        if (this.isEditMode) {
            this.resetOnEdit();
        } else {
            this.resetOnCreate();
        }
    }

    private resetOnCreate() {
        this.newUserForm.reset({
            usuarioTypeEnum: UsuarioTypeEnum.Usuario
        });
    }

    private resetOnEdit() {
        this.newUserForm.reset(this.userToUpdate)
    }

    private saveNewUser() {
        this.newUser = this.newUserForm.value as UsuarioRequest;
        this.userService.createNewUser(this.newUser).subscribe({
            next: (usuarioResponse: UsuarioResponse) => {
                console.log('Usuario creado:', usuarioResponse);
                this.exception = false;
                this.operationDone = true;
                this.reset();
            },
            error: (error: any) => {
                this.reset();
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
    private updateUser() {
        console.log('Actualizando usuario...');
        this.userToUpdate = this.newUserForm.value as UsuarioResponse;
        const { usuario_Id, ...userToUpdate } = this.userToUpdate;
        this.userService.updateUser(usuario_Id.toString(), userToUpdate).subscribe({
            next: (usuarioResponse: UsuarioResponse) => {
                this.reset();
                this.exception = false;
                this.operationDone = true;
                console.log('Usuario actualizado:', usuarioResponse);

            },
            error: (error: any) => {
                this.reset();
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
        })
    }

}