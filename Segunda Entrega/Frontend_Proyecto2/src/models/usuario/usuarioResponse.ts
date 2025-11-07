import { UsuarioTypeEnum } from "./usuarioTypeEnum";

export interface UsuarioResponse {
    usuario_Id: number;
    nombre: string;
    email: string;
    contraseña: string;
    usuarioTypeEnum: UsuarioTypeEnum;
}