import { Component, Input, OnInit, Type } from "@angular/core";
import { UsuarioResponse } from "../../../models/usuario/usuarioResponse";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { KeyValuePipe, NgFor } from "@angular/common";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { CineResponse } from "../../../models/cine/cineResponse";
import { CineRequest } from "../../../models/cine/CineRequest";
import { CineService } from "../../../services/cine/user.service";
import { EstadoTypeEnum } from "../../../models/typeEnumEstado/EstadoTypeEnum";

@Component({
    selector: 'app-cine-form',
    imports: [NgFor, FormsModule, ReactiveFormsModule, KeyValuePipe],
    templateUrl: './cine-form.component.html'
})
export class CineFormComponent implements OnInit {

    @Input()
    isEditMode: boolean = false;
    @Input()
    cineToUpdate!: CineResponse;
    newCineForm!: FormGroup;
    newCine!: CineRequest;
    usuarioTypeEnums = UsuarioTypeEnum;
    estadoTypeEnums = EstadoTypeEnum;
    operationDone: boolean = false;

    exception: boolean = false;
    mensajeError!: string;

    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;

    constructor(private formBuilder: FormBuilder,
        private cineService: CineService
    ) {

    }

    ngOnInit(): void {
        this.newCineForm = this.formBuilder.group({
            cine_Id: [null],
            nombre: [null, [Validators.required, Validators.maxLength(200)]],
            telefono: [null, [Validators.required, Validators.maxLength(10)]],
            direccion: [null, [Validators.required,Validators.maxLength(200)]],
            estadoTypeEnum: [null, [Validators.required]],
            fechaCreacion: [new Date().toISOString().substring(0, 10), Validators.required]
        });
        this.reset();
    }

    estadoTypeOptions = Object.keys(EstadoTypeEnum).filter(val => isNaN(Number(val))).map(key => ({
        key: key,
        value: EstadoTypeEnum[key as keyof typeof EstadoTypeEnum]
    }));

    submit(): void {
        console.log('se hizo submit');
        if (this.newCineForm.valid) {
            console.log('Formulario válido');
            if (this.isEditMode) {
                this.updateCine();
            } else {
                this.saveNewCine();
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
        this.newCineForm.reset({
            estadoTypeEnum: EstadoTypeEnum.HABILITADO,
            fechaCreacion: new Date().toISOString().substring(0, 10)
        });
    }

    private resetOnEdit() {
        this.newCineForm.reset(this.cineToUpdate)
    }

    private saveNewCine() {
        this.newCine = this.newCineForm.value as CineRequest;
        console.log('Nuevo cine a crear:', this.newCine);
        this.cineService.createNewCine(this.newCine).subscribe({
            next: (cineResponse: CineResponse) => {
                console.log('Cine creado:', cineResponse);
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
    private updateCine() {
        this.cineToUpdate = this.newCineForm.value as CineResponse;
        const { cine_Id, ...cineToUpdate } = this.cineToUpdate;
        this.cineService.updateCine(cine_Id.toString(), cineToUpdate).subscribe({
            next: (cineResponse: CineResponse) => {
                this.reset();
                this.exception = false;
                this.operationDone = true;
                console.log('Cine actualizado:', cineResponse);
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