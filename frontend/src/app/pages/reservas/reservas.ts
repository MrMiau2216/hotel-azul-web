import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { Reserva } from '../../models/reserva';
import { Habitacion } from '../../models/habitacion';
import { Huesped } from '../../models/huesped';
import { ReservaService } from '../../services/reserva.service';
import { HabitacionService } from '../../services/habitacion.service';
import { HuespedService } from '../../services/huesped.service';

@Component({
  selector: 'app-reservas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reservas.html',
  styleUrl: './reservas.css'
})
export class Reservas implements OnInit {
  reservas: Reserva[] = [];
  habitaciones: Habitacion[] = [];
  huespedes: Huesped[] = [];

  idEnEdicion: number | null = null;
  habitacionId: number = 0;
  huespedId: number = 0;
  fechaEntrada: string = '';
  fechaSalida: string = '';
  estado: string = 'PENDIENTE';
  total: number = 0;

  constructor(
    private reservaService: ReservaService,
    private habitacionService: HabitacionService,
    private huespedService: HuespedService
  ) { }

  ngOnInit(): void {
    this.cargarReservas();

    this.habitacionService.listar().subscribe({
      next: (data) => {
        this.habitaciones = data;
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    });

    this.huespedService.listar().subscribe({
      next: (data) => {
        this.huespedes = data;
      },
      error: (error) => {
        console.error('Error al obtener los huéspedes:', error);
      }
    });
  }

  cargarReservas(): void {
    this.reservaService.listar().subscribe({
      next: (data) => {
        this.reservas = data;
      },
      error: (error) => {
        console.error('Error al obtener las reservas:', error);
      }
    });
  }

  limpiarFormulario(): void {
    this.idEnEdicion = null;
    this.habitacionId = 0;
    this.huespedId = 0;
    this.fechaEntrada = '';
    this.fechaSalida = '';
    this.estado = 'PENDIENTE';
    this.total = 0;
  }

  editar(reserva: Reserva): void {
    this.idEnEdicion = reserva.id!;
    this.habitacionId = reserva.habitacion.id!;
    this.huespedId = reserva.huesped.id!;
    this.fechaEntrada = reserva.fechaEntrada;
    this.fechaSalida = reserva.fechaSalida;
    this.estado = reserva.estado;
    this.total = reserva.total;
  }

  guardar(): void {
    if (this.habitacionId === 0 || this.huespedId === 0) {
      alert('Debe seleccionar habitación y huésped.');
      return;
    }

    const reserva: Reserva = {
      habitacion: { id: this.habitacionId } as Habitacion,
      huesped: { id: this.huespedId } as Huesped,
      fechaEntrada: this.fechaEntrada,
      fechaSalida: this.fechaSalida,
      estado: this.estado,
      total: this.total
    };

    if (this.idEnEdicion) {
      this.reservaService.actualizar(this.idEnEdicion, reserva).subscribe({
        next: () => {
          this.cargarReservas();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo actualizar la reserva.');
        }
      });
    } else {
      this.reservaService.crear(reserva).subscribe({
        next: () => {
          this.cargarReservas();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo registrar la reserva.');
        }
      });
    }
  }

  eliminar(id: number): void {
    if (!confirm('¿Está seguro de eliminar esta reserva?')) return;
    this.reservaService.eliminar(id).subscribe({
      next: () => {
        this.cargarReservas();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error || 'No se pudo eliminar la reserva.');
      }
    });
  }
}
