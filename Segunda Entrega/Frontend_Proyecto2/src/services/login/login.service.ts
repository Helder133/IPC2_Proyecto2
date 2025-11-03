import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { RestConstants } from "../../shared/restapi/rest-constants";
import { Login } from "../../models/login/login";
import { Observable } from 'rxjs';
import { RequestLogin } from "../../models/login/requestLogin";

@Injectable({
    providedIn: 'root'
})

export class loginService {
    restConstants = new RestConstants();

    constructor(private httpClient: HttpClient) { }

    public loginUser(newLogin: Login): Observable<RequestLogin> {
        return this.httpClient.post<RequestLogin>(`${this.restConstants.getApiURL()}login`, newLogin);
    }
}