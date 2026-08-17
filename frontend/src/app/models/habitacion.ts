import { TipoHabitacion } from './tipo-habitacion';

export interface Habitacion {
  id?: number;
  numero: string;
  piso: number;
  precio: number;
  urlImagen: string;
  descripcion: string;
  estado: string;
  tipo: TipoHabitacion;
}
