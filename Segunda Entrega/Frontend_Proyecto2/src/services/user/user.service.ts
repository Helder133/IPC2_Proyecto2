import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RestConstants } from "../../shared/restapi/rest-constants";
import { UsuarioRequest } from "../../models/usuario/usuarioRequest";
import { UsuarioResponse } from "../../models/usuario/usuarioResponse";
import { Observable } from "rxjs";
import { UsuarioUpdateRequest } from "../../models/usuario/usuarioUpdateRequest";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public createNewUser(usuarioRequest: UsuarioRequest): Observable<UsuarioResponse> {
        return this.httpClient.post<UsuarioResponse>(`${this.restConstants.getApiURL()}users`, usuarioRequest);
    }

    public getAllUsers(): Observable<UsuarioResponse[]> {
        return this.httpClient.get<UsuarioResponse[]>(`${this.restConstants.getApiURL()}users`);
    }

    public getUserByCode(code: string): Observable<UsuarioResponse[]> {
        return this.httpClient.get<UsuarioResponse[]>(`${this.restConstants.getApiURL()}users/${code}`);
    }

    public getUserByCodeNumber(code: number): Observable<UsuarioResponse> {
        return this.httpClient.get<UsuarioResponse>(`${this.restConstants.getApiURL()}users/${code}`);
    }

    public updateUser(code: string, usuarioToUpdate: UsuarioUpdateRequest): Observable<UsuarioResponse> {
        return this.httpClient.put<UsuarioResponse>(`${this.restConstants.getApiURL()}users/${code}`, usuarioToUpdate);
    }

    public deleteUser(code: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.restConstants.getApiURL()}users/${code}`);
    }
}