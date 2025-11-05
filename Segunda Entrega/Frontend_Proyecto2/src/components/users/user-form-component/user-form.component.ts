import { Component, Input, OnInit } from "@angular/core";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { UsuarioRequest } from "../../../models/usuario/usuarioRequest";
import { UserService } from "../../../services/user/user.service";
import { KeyValuePipe } from "@angular/common";

@Component({
    selector: 'app-user-form',
    imports: [FormsModule, ReactiveFormsModule],
    templateUrl: './user-form.component.html'
})
export class UserFormComponent implements OnInit {
    @Input()
    isEditMode: boolean = false;
    @Input()
    userToUpdate!: UsuarioRequest;
    newUserForm!: FormGroup;
    newUser!: UsuarioRequest;
    operationDone: boolean = false;
    exception: boolean = false;
    mensajeError!: string;
    codigoPorDeFecto: number = 90;
    roles = ["USUARIO", "ADMINISTRADOR SISTEMA", "ADMINISTRADOR CINE"];

    constructor(private formBuilder: FormBuilder,
        private userService: UserService
    ) {

    }

    ngOnInit(): void {
            this.newUserForm = this.formBuilder.group({
            usuario_Id: [1, [Validators.required]],
            nombre: [null, [Validators.required, Validators.maxLength(200)]],
            email: [null, [Validators.required, Validators.email, Validators.maxLength(100)]],
            contraseña: [null, [Validators.required, Validators.maxLength(50)]],
            rol: [this.roles[0], [Validators.required]]
        });
    this.reset();
    }

    submit(): void {
        console.log('se hizo submit');
        if(this.newUserForm.valid) {
            if(this.isEditMode) {
                this.updateUser();
            }else{
                this.saveNewUser();
            }
        }
    }
    private saveNewUser() {
        this.newUser = this.newUserForm.value as UsuarioRequest;
        this.userService.createNewUser(this.newUser).subscribe({
            next: (usuarioResponse: UsuarioResponse) =>{
                console.log('Usuario creado:', usuarioResponse);
                this.operationDone = true;
                this.reset();
            },
            error: (error: any) => {
                this.reset();
                this.exception = true;
            }
        });
    }
    private updateUser() {
        this.userToUpdate = this.newUserForm.value as UsuarioRequest;
        const {usuario_Id, ...userToUpdate} = this.userToUpdate;
        this.userService.updateUser(usuario_Id.toString(), userToUpdate).subscribe({
            next: (usuarioResponse: UsuarioResponse) => {
                this.reset();
                this.operationDone = true;
                console.log('Usuario actualizado:', usuarioResponse);
                
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
        })
    }

    reset(): void {
        if (this.isEditMode) {
            this.resetOnEdit();
        } else {
            this.resetOnCreate();
        }
    }
    private resetOnCreate() {
        this.newUserForm.reset();
    }

    private resetOnEdit() {
        this.newUserForm.reset(this.userToUpdate)
    }
}