import { EstadoTypeEnum } from "../typeEnumEstado/EstadoTypeEnum";

export interface CineResponse {
    cine_Id: number;
    nombre: string;
    telefono: string;
    direccion: string;
    estadoTypeEnum: EstadoTypeEnum;
    fechaCreacion: Date;
}