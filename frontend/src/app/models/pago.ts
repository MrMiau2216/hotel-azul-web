import { Reserva } from './reserva';

export interface Pago {
  id?: number;
  reserva: Reserva;
  monto: number;
  metodo: string;
  fechaPago?: string;
}
