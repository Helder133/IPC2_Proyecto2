import { UsuarioTypeEnum } from "./usuarioTypeEnum";

export interface UsuarioUpdateRequest {
    nombre: string;
    email: string;
    contraseña: string;
    usuarioTypeEnum: UsuarioTypeEnum;
}