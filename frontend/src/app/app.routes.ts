import { Routes } from '@angular/router';
import { Inicio } from './pages/inicio/inicio';
import { Login } from './pages/login/login';
import { Habitaciones } from './pages/habitaciones/habitaciones';
import { Huespedes } from './pages/huespedes/huespedes';
import { Reservas } from './pages/reservas/reservas';
import { Pagos } from './pages/pagos/pagos';

export const routes: Routes = [
  {
    path: '',
    component: Inicio
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'habitaciones',
    component: Habitaciones
  },
  {
    path: 'huespedes',
    component: Huespedes
  },
  {
    path: 'reservas',
    component: Reservas
  },
  {
    path: 'pagos',
    component: Pagos
  }
];
