import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, RouterLink } from "@angular/router";
import { HeaderComponent } from "../../../components/header/header.component";
import { ClasificacionFormComponent } from "../../../components/clasificacion/clasificacion-form-component/clasificacion-form.component";
import { ClasificacionResponse } from "../../../models/clasificacion/ClasificacionResponse";
import { ClasificacionService } from "../../../services/clasificacion/clasificacion.service";

@Component({
  selector: 'app-update-clasificacion-page',
  imports: [ClasificacionFormComponent, RouterLink, HeaderComponent],
  templateUrl: './update-clasificacion-page.component.html',
})
export class UpdateClasificacionPageComponent implements OnInit {
  clasificacionCode!: number;
  clasificacionToUpdate!: ClasificacionResponse;
  exists: boolean = false;
  exception: boolean = false;
  mensajeError!: string;

  constructor(private router: ActivatedRoute, 
    private clasificacionService: ClasificacionService) {

  }

  ngOnInit(): void {
    this.clasificacionCode = this.router.snapshot.params['code'];
    this.clasificacionService.getClasificacionByCodeNumber(this.clasificacionCode).subscribe({
      next: (clasificacionToUpdate) => {
        this.clasificacionToUpdate = clasificacionToUpdate;
        this.exists = true;
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