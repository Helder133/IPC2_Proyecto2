import { EstadoTypeEnum } from "../typeEnumEstado/EstadoTypeEnum";

export interface CineUpdate {
    nombre: string;
    telefono: string;
    direccion: string;
    estadoTypeEnum: EstadoTypeEnum;
    fechaCreacion: Date;
}