import { Component, Input, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { ClasificacionResponse } from "../../../models/clasificacion/ClasificacionResponse";
import { ClasificacionRequest } from "../../../models/clasificacion/ClasificacionRequest";
import { ClasificacionService } from "../../../services/clasificacion/clasificacion.service";

@Component({
    selector: 'app-clasificacion-form',
    imports: [ FormsModule, ReactiveFormsModule],
    templateUrl: './clasificacion-form.component.html'
})
export class ClasificacionFormComponent implements OnInit {

    @Input()
    isEditMode: boolean = false;
    @Input()
    clasificacionToUpdate!: ClasificacionResponse;

    newClasificacionForm!: FormGroup;
    newClasificacion!: ClasificacionRequest;
    usuarioTypeEnums = UsuarioTypeEnum;
    operationDone: boolean = false;

    exception: boolean = false;
    mensajeError!: string;

    isAdmin: boolean = localStorage.getItem('rol') == this.usuarioTypeEnums.Administrador_Sistema;

    constructor(private formBuilder: FormBuilder,
        private clasificacionService: ClasificacionService
    ) {

    }

    ngOnInit(): void {
        this.newClasificacionForm = this.formBuilder.group({
            clasificacion_Id: [null],
            nombre: [null, [Validators.required, Validators.maxLength(150)]],
            edadMinima: [null, [Validators.required]],
            descripcion: [null, [Validators.required, Validators.maxLength(250)]]
        });
        this.reset();
    }

    submit(): void {
        console.log('se hizo submit');
        if (this.newClasificacionForm.valid) {
            if (this.isEditMode) {
                this.updateClasificacion();
            } else {
                this.saveNewClasificacion();
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
        this.newClasificacionForm.reset({});
    }

    private resetOnEdit() {
        this.newClasificacionForm.reset(this.clasificacionToUpdate)
    }

    private saveNewClasificacion() {
        this.newClasificacion = this.newClasificacionForm.value as ClasificacionRequest;
        console.log(this.newClasificacionForm.value)
        this.clasificacionService.createNewClasificacion(this.newClasificacion).subscribe({
            next: () => {
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
    private updateClasificacion() {
        this.clasificacionToUpdate = this.newClasificacionForm.value as ClasificacionResponse;
        const { clasificacion_Id, ...clasificacionToUpdate } = this.clasificacionToUpdate;
        this.clasificacionService.updateClasificacion(clasificacion_Id.toString(), clasificacionToUpdate).subscribe({
            next: () => {
                this.reset();
                this.exception = false;
                this.operationDone = true;

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