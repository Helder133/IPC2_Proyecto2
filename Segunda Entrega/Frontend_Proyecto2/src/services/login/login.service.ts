import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { RestConstants } from "../../shared/restapi/rest-constants";
import { RequestLogin } from "../../models/login/requestlogin";
import { Observable } from 'rxjs';
import { ResponseLogin } from "../../models/login/responseLogin";
import { UsuarioResponse } from "../../models/usuario/usuarioResponse";

@Injectable({
    providedIn: 'root'
})

export class loginService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public loginUser(formLogin: RequestLogin): Observable<UsuarioResponse> {
        return this.httpClient.post<UsuarioResponse>(`${this.restConstants.getApiURL()}login`, formLogin);
    }
}