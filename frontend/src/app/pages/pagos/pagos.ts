import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { Pago } from '../../models/pago';
import { Reserva } from '../../models/reserva';
import { PagoService } from '../../services/pago.service';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-pagos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pagos.html',
  styleUrl: './pagos.css'
})
export class Pagos implements OnInit {
  pagos: Pago[] = [];
  reservas: Reserva[] = [];

  idEnEdicion: number | null = null;
  reservaId: number = 0;
  monto: number = 0;
  metodo: string = 'EFECTIVO';

  constructor(
    private pagoService: PagoService,
    private reservaService: ReservaService
  ) { }

  ngOnInit(): void {
    this.cargarPagos();

    this.reservaService.listar().subscribe({
      next: (data) => {
        this.reservas = data;
      },
      error: (error) => {
        console.error('Error al obtener las reservas:', error);
      }
    });
  }

  cargarPagos(): void {
    this.pagoService.listar().subscribe({
      next: (data) => {
        this.pagos = data;
      },
      error: (error) => {
        console.error('Error al obtener los pagos:', error);
      }
    });
  }

  limpiarFormulario(): void {
    this.idEnEdicion = null;
    this.reservaId = 0;
    this.monto = 0;
    this.metodo = 'EFECTIVO';
  }

  editar(pago: Pago): void {
    this.idEnEdicion = pago.id!;
    this.reservaId = pago.reserva.id!;
    this.monto = pago.monto;
    this.metodo = pago.metodo;
  }

  guardar(): void {
    if (this.reservaId === 0) {
      alert('Debe seleccionar una reserva.');
      return;
    }

    const pago: Pago = {
      reserva: { id: this.reservaId } as Reserva,
      monto: this.monto,
      metodo: this.metodo
    };

    if (this.idEnEdicion) {
      this.pagoService.actualizar(this.idEnEdicion, pago).subscribe({
        next: () => {
          this.cargarPagos();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo actualizar el pago.');
        }
      });
    } else {
      this.pagoService.crear(pago).subscribe({
        next: () => {
          this.cargarPagos();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo registrar el pago.');
        }
      });
    }
  }

  eliminar(id: number): void {
    if (!confirm('¿Está seguro de eliminar este pago?')) return;
    this.pagoService.eliminar(id).subscribe({
      next: () => {
        this.cargarPagos();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error || 'No se pudo eliminar el pago.');
      }
    });
  }
}
