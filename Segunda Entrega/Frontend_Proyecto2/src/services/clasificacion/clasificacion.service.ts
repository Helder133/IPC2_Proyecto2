import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Observable } from "rxjs";
import { ClasificacionResponse } from "../../models/clasificacion/ClasificacionResponse";
import { ClasificacionRequest } from "../../models/clasificacion/ClasificacionRequest";
import { ClasificacionUpdate } from "../../models/clasificacion/ClasificacionUpdate";

@Injectable({
    providedIn: 'root'
})
export class ClasificacionService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public createNewClasificacion(clasificacionRequest: ClasificacionRequest): Observable<void> {
        return this.httpClient.post<void>(`${this.restConstants.getApiURL()}clasificaciones`, clasificacionRequest);
    }

    public getAllClasificaciones(): Observable<ClasificacionResponse[]> {
        return this.httpClient.get<ClasificacionResponse[]>(`${this.restConstants.getApiURL()}clasificaciones`);
    }

    public getClasificacionByCode(code: string): Observable<ClasificacionResponse[]> {
        return this.httpClient.get<ClasificacionResponse[]>(`${this.restConstants.getApiURL()}clasificaciones/${code}`);
    }

    public getClasificacionByCodeNumber(code: number): Observable<ClasificacionResponse> {
        return this.httpClient.get<ClasificacionResponse>(`${this.restConstants.getApiURL()}clasificaciones/${code}`);
    }

    public updateClasificacion(code: string, clasificacionUpdateData: ClasificacionUpdate): Observable<void> {
        return this.httpClient.put<void>(`${this.restConstants.getApiURL()}clasificaciones/${code}`, clasificacionUpdateData);
    }

    public deleteClasificacion(code: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}clasificaciones/${code}`);
    }
}