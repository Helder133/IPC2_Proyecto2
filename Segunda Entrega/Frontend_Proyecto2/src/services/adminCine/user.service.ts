import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Observable } from "rxjs";
import { UsuarioUpdateRequest } from "../../models/usuario/usuarioUpdateRequest";
import { AdminCineRequest } from "../../models/adminCine/AdminCineRequest";
import { AdminCineResponse } from "../../models/adminCine/AdminCineResponse";

@Injectable({
    providedIn: 'root'
})
export class AdminCineService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public createNewAdminCine(adminCineRequest: AdminCineRequest): Observable<AdminCineResponse> {
        return this.httpClient.post<AdminCineResponse>(`${this.restConstants.getApiURL()}adminCines`, adminCineRequest);
    }

    public getAllAdminCines(): Observable<AdminCineResponse[]> {
        return this.httpClient.get<AdminCineResponse[]>(`${this.restConstants.getApiURL()}adminCines`);
    }

    public getAdminCineByCode(code: string): Observable<AdminCineResponse[]> {
        return this.httpClient.get<AdminCineResponse[]>(`${this.restConstants.getApiURL()}adminCines/${code}`);
    }

    public getAdminCineByCodeNumber(code: number): Observable<AdminCineResponse> {
        return this.httpClient.get<AdminCineResponse>(`${this.restConstants.getApiURL()}adminCines/${code}`);
    }
/*
    public updateAdminCine(code: string, usuarioToUpdate: UsuarioUpdateRequest): Observable<UsuarioResponse> {
        return this.httpClient.put<UsuarioResponse>(`${this.restConstants.getApiURL()}adminCines/${code}`, usuarioToUpdate);
    }
*/
    public deleteAdminCine(code: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}adminCines/${code}`);
    }
}