import { Component, OnInit } from "@angular/core";
import { HeaderComponent } from "../../../components/header/header.component";
import { CineFormComponent } from "../../../components/cine/cine-form-component/cine-form.component";
import { ActivatedRoute, RouterLink } from "@angular/router";
import { CineResponse } from "../../../models/cine/cineResponse";
import { CineService } from "../../../services/cine/user.service";

@Component({
    selector: 'app-update-cine-page',
    imports: [HeaderComponent, CineFormComponent, RouterLink],
    templateUrl: './update-cine-page.component.html'
})

export class UpdateCinePageComponent implements OnInit {
    cineCode!: number;
    cineToUpdate!: CineResponse;
    exists: boolean = false;
    exception!: boolean;
    mensajeError!: string;

    constructor(private router: ActivatedRoute, private cineService: CineService) {

    }

    ngOnInit(): void {
        this.cineCode =this.router.snapshot.params['code'];
        this.cineService.getCineByCodeNumber(this.cineCode).subscribe({
            next: (cineToUpdate) => {
                this.cineToUpdate = cineToUpdate;
                this.exists = true;
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