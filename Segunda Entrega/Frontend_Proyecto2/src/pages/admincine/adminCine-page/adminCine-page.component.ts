import { Component, OnInit } from "@angular/core";
import { HeaderComponent } from "../../../components/header/header.component";
import { AdminCineCardComponent } from "../../../components/adminCine/adminCine-card-component/adminCine-card.component";
import { RouterLink } from "@angular/router";
import { FormBuilder, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UsuarioTypeEnum } from "../../../models/usuario/usuarioTypeEnum";
import { AdminCineResponse } from "../../../models/adminCine/AdminCineResponse";
import { AdminCineService } from "../../../services/adminCine/user.service";
import { RoleGuardService } from "../../../services/security/role-guard.service";
import { ConfirmationModalAdminCineComponent } from "../../../components/confirmation-modal/confirmation-modal-adminCine.component/confirmation-modal-adminCIne.component";

@Component({
    selector: 'app-adminCine-page',
    imports: [HeaderComponent, AdminCineCardComponent, RouterLink, FormsModule, ReactiveFormsModule, ConfirmationModalAdminCineComponent],
    templateUrl: './adminCine-page.component.html'
})
export class AdminCinePageComponent implements OnInit{
    usuarioTypeEnums = UsuarioTypeEnum;
    protected adminCines: AdminCineResponse[] = [];
    selectedAdminCine!: AdminCineResponse;
    deleted: boolean = false;
    isAdmin: boolean;

    exception: boolean = false;
    mensajeError: string = "";

    constructor(private adminCineService: AdminCineService,
        private roleGuardService: RoleGuardService, private formBuilder: FormBuilder) {
        this.isAdmin = roleGuardService.userRoleInAllowedRoles([this.usuarioTypeEnums.Administrador_Sistema]);
    }
    ngOnInit(): void {
        this.loadAdminCines();
    }

    onSelectedAdminCine(adminCine: AdminCineResponse): void {
        this.selectedAdminCine = adminCine;
        this.deleted = false;
    }

    private loadAdminCines(): void {
        this.adminCineService.getAllAdminCines().subscribe({
            next: (adminCinesFromServer: AdminCineResponse[]) => {
                this.adminCines = adminCinesFromServer;
            }, error: (error: any) => {
                this.exception = true;
                // Si el backend envía un JSON con el campo "error"
                if (error.error && error.error.error) {
                    this.mensajeError = error.error.error;
                }
            }
        })
    }

    deleteAdminCine(): void {
        this.adminCineService.deleteAdminCine(this.selectedAdminCine.usuario_Id).subscribe({
            next: () => {
                this.deleted = true;
                this.loadAdminCines();
            }, error: (error: any) => {
                this.exception = true;
                if (error.error && error.error.error) {
                    this.mensajeError = error.error.error;
                }
            }
        });
    }
} 