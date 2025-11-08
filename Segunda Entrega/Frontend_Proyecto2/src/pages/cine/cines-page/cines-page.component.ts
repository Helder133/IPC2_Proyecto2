import { Component, OnInit } from "@angular/core";
import { HeaderComponent } from "../../../components/header/header.component";
import { CineCardComponent } from "../../../components/cine/cine-card-component/cine-card.component";
import { RouterLink } from "@angular/router";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { CineResponse } from "../../../models/cine/cineResponse";
import { CineService } from "../../../services/cine/user.service";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { ConfirmationModalCineComponent } from "../../../components/confirmation-modal/confirmation-modal-cine.component/confirmation-modal-user.component";

@Component({
    selector: 'app-cines-page',
    imports: [HeaderComponent, CineCardComponent, ConfirmationModalCineComponent, RouterLink, FormsModule, ReactiveFormsModule],
    templateUrl: './cines-page.component.html'
})
export class CinesPageComponent implements OnInit{
    usuarioTypeEnums = UsuarioTypeEnum;
    protected cines: CineResponse[] = [];
    selectedCine!: CineResponse;
    deleted: boolean = false;
    isAdmin: boolean;

    nombreABuscarForm!: FormGroup;

    exception: boolean = false;
    mensajeError: string = "";

    constructor(private cineService: CineService,
        private roleGuardService: RoleGuardService, private formBuilder: FormBuilder) {
        this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usuarioTypeEnums.Administrador_Sistema]);
    }
    ngOnInit(): void {
        this.loadCines();
        this.nombreABuscarForm = this.formBuilder.group({
            nombre: [null]
        });
    }

    onSelectedCine(cine: CineResponse): void {
        this.selectedCine = cine;
        this.deleted = false;
    }

    buscarPorNombre(): void {
        if (this.nombreABuscarForm.value.nombre == null) {
            this.loadCines();
            return;
        }
        this.cineService.getCineByCode(this.nombreABuscarForm.value.nombre).subscribe({
            next: (cineFromServer: CineResponse[]) => {
                this.cines = cineFromServer;
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

    private loadCines() {
        this.cineService.getAllCines().subscribe({
            next: (cinesFromServer: CineResponse[]) => {
                this.cines = cinesFromServer;
                console.log('Cines cargados:', this.cines);
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

    deleteCine(): void {
        this.cineService.deleteCine(this.selectedCine.cine_Id).subscribe({
            next: () => {
                this.deleted = true;
                this.loadCines();
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
        })
    }
}