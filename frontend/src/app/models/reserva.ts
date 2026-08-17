import { Habitacion } from './habitacion';
import { Huesped } from './huesped';

export interface Reserva {
  id?: number;
  habitacion: Habitacion;
  huesped: Huesped;
  fechaEntrada: string;
  fechaSalida: string;
  fechaRegistro?: string;
  estado: string;
  total: number;
}
