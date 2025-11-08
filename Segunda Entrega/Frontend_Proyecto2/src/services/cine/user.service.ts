import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { UsuarioRequest } from "../../models/usuario/usuarioRequest";
import { UsuarioResponse } from "../../models/usuario/usuarioResponse";
import { Observable } from "rxjs";
import { UsuarioUpdateRequest } from "../../models/usuario/usuarioUpdateRequest";
import { CineRequest } from "../../models/cine/CineRequest";
import { CineResponse } from "../../models/cine/cineResponse";
import { CineUpdate } from "../../models/cine/CineUpdate";

@Injectable({
    providedIn: 'root'
})
export class CineService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public createNewCine(cineRequest: CineRequest): Observable<CineResponse> {
        return this.httpClient.post<CineResponse>(`${this.restConstants.getApiURL()}cines`, cineRequest);
    }

    public getAllCines(): Observable<CineResponse[]> {
        return this.httpClient.get<CineResponse[]>(`${this.restConstants.getApiURL()}cines`);
    }

    public getCineByCode(code: string): Observable<CineResponse[]> {
        return this.httpClient.get<CineResponse[]>(`${this.restConstants.getApiURL()}cines/${code}`);
    }

    public getCineByCodeNumber(code: number): Observable<CineResponse> {
        return this.httpClient.get<CineResponse>(`${this.restConstants.getApiURL()}cines/${code}`);
    }

    public updateCine(code: string, cineToUpdate: CineUpdate): Observable<CineResponse> {
        return this.httpClient.put<CineResponse>(`${this.restConstants.getApiURL()}cines/${code}`, cineToUpdate);
    }

    public deleteCine(code: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}cines/${code}`);
    }
}