import { Component, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";
import { HeaderComponent } from "../../../components/header/header.component";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ClasificacionResponse } from "../../../models/clasificacion/ClasificacionResponse";
import { ClasificacionService } from "../../../services/clasificacion/clasificacion.service";
import { ClasificacionCardComponent } from "../../../components/clasificacion/clasificacion-card-component/clasificacion-card.component";
import { ConfirmationModalClasificacionComponent } from "../../../components/confirmation-modal/confirmation-modal-clasificacion.component/confirmation-modal-clasificacion.component";

@Component({
    selector: 'app-clasificaciones-page',
    imports: [HeaderComponent, ClasificacionCardComponent, RouterLink, ConfirmationModalClasificacionComponent, FormsModule, ReactiveFormsModule],
    templateUrl: './clasificacion-page.component.html',
})

export class ClasificacionPageComponent implements OnInit {

    usuarioTypeEnums = UsuarioTypeEnum;
    protected clasificaciones: ClasificacionResponse[] = [];
    selectedClasificacion!: ClasificacionResponse;
    deleted: boolean = false;
    isAdmin: boolean;

    nombreABuscar!: FormGroup;
    exception: boolean = false;
    mensajeError: string = "";

    constructor(private clasificacionService: ClasificacionService,
        private roleGuardService: RoleGuardService, private formBuilder: FormBuilder) {
        this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usuarioTypeEnums.Administrador_Sistema]);
    }
    ngOnInit(): void {
        this.loadClasificaciones();
        this.nombreABuscar = this.formBuilder.group({
            nombre: [null]
        });
    }

    onSelectedClasificacion(clasificacion: ClasificacionResponse): void {
        this.selectedClasificacion = clasificacion;
        this.deleted = false;
    }

    buscarPorNombre(): void {
        if (this.nombreABuscar.value.nombre == null) {
            this.loadClasificaciones();
            return;
        }
        this.clasificacionService.getClasificacionByCode(this.nombreABuscar.value.nombre).subscribe({
            next: (clasificacionFromServer: ClasificacionResponse[]) => {
                this.clasificaciones = clasificacionFromServer;
            }, error: (error: any) => {
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

    private loadClasificaciones(): void {
        this.clasificacionService.getAllClasificaciones().subscribe({
            next: (clasificacionesFromServer: ClasificacionResponse[]) => {
                console.log(clasificacionesFromServer);
                this.clasificaciones = clasificacionesFromServer;
            }, error: (error: any) => {
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

    deleteClasificacion(): void {
        this.clasificacionService.deleteClasificacion(this.selectedClasificacion.clasificacion_Id).subscribe({
            next: () => {
                this.loadClasificaciones();
                this.deleted = true;
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
}