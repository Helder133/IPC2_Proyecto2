import { EstadoTypeEnum } from "../typeEnumEstado/EstadoTypeEnum";

export interface CineRequest {
    nombre: string;
    telefono: string;
    direccion: string;
    estadoTypeEnum: EstadoTypeEnum;
    fechaCreacion: Date;
}