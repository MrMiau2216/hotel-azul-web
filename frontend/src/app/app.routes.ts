import { Routes } from '@angular/router';
import { Inicio } from './pages/inicio/inicio';
import { Nosotros } from './pages/nosotros/nosotros';
import { HabitacionesCatalogo } from './pages/habitaciones-catalogo/habitaciones-catalogo';
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
    path: 'nosotros',
    component: Nosotros
  },
  {
    path: 'habitaciones-catalogo',
    component: HabitacionesCatalogo
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
