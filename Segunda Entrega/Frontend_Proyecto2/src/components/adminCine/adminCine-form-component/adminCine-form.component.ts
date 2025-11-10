import { Component, Input, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { KeyValuePipe, NgFor } from "@angular/common";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { AdminCineResponse } from "../../../models/adminCine/AdminCineResponse";
import { AdminCineRequest } from "../../../models/adminCine/AdminCineRequest";
import { AdminCineService } from "../../../services/adminCine/user.service";

@Component({
    selector: 'app-adminCine-form',
    imports: [FormsModule, ReactiveFormsModule],
    templateUrl: './adminCine-form.component.html'
})
export class AdminCineFormComponent implements OnInit {

    @Input()
    isEditMode: boolean = false;
    @Input()
    adminCineToUpdate!: AdminCineResponse;

    newAdminCineForm!: FormGroup;
    newAdminCine!: AdminCineRequest;
    usuarioTypeEnums = UsuarioTypeEnum;
    operationDone: boolean = false;

    exception: boolean = false;
    mensajeError!: string;

    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;

    constructor(private formBuilder: FormBuilder,
        private adminCineService: AdminCineService
    ) {

    }

    ngOnInit(): void {
        this.newAdminCineForm = this.formBuilder.group({
            email: [null, [Validators.required, Validators.email, Validators.maxLength(100)]],
            telefono: [null, [Validators.maxLength(10), Validators.required]],
        });
        this.reset();
        this.applyPasswordValidators();
    }

    usuarioTypeOptions = Object.keys(UsuarioTypeEnum).filter(val => isNaN(Number(val))).map(key => ({
        key: key,
        value: UsuarioTypeEnum[key as keyof typeof UsuarioTypeEnum]
    }));

    private applyPasswordValidators(): void {
    const passControl = this.newAdminCineForm.get('contraseña');
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
        if (this.newAdminCineForm.valid) {
            console.log('Formulario válido');
            if (this.isEditMode) {
                //this.updateAdminCine();
            } else {
                this.saveNewAdminCine();
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
        this.newAdminCineForm.reset();
    }

    private resetOnEdit() {
        this.newAdminCineForm.reset(this.adminCineToUpdate)
    }

    private saveNewAdminCine() {
        this.newAdminCine = this.newAdminCineForm.value as AdminCineRequest;
        this.adminCineService.createNewAdminCine(this.newAdminCine).subscribe({
            next: (adminCineResponse: AdminCineResponse) => {
                console.log('AdminCine creado:', adminCineResponse);
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
    /* 
    private updateUser() {
        console.log('Actualizando usuario...');
        this.adminCineToUpdate = this.newAdminCineForm.value as UsuarioResponse;
        const { usuario_Id, ...userToUpdate } = this.adminCineToUpdate;
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
    */
}