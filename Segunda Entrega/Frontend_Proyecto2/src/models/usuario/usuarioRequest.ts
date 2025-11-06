import { UsuarioTypeEnum } from "./usuarioTypeEnum";

export interface UsuarioRequest {
    usuario_Id: number;
    nombre: string;
    email: string;
    contraseña: string;
    usuarioTypeEnum: UsuarioTypeEnum;
}
