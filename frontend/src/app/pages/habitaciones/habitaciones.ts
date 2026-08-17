import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { Habitacion } from '../../models/habitacion';
import { TipoHabitacion } from '../../models/tipo-habitacion';
import { HabitacionService } from '../../services/habitacion.service';
import { TipoHabitacionService } from '../../services/tipo-habitacion.service';

@Component({
  selector: 'app-habitaciones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './habitaciones.html',
  styleUrl: './habitaciones.css'
})
export class Habitaciones implements OnInit {
  habitaciones: Habitacion[] = [];
  tipos: TipoHabitacion[] = [];

  idEnEdicion: number | null = null;
  numero: string = '';
  piso: number = 1;
  precio: number = 0;
  urlImagen: string = '';
  descripcion: string = '';
  estado: string = 'DISPONIBLE';
  tipoId: number = 0;

  constructor(
    private habitacionService: HabitacionService,
    private tipoHabitacionService: TipoHabitacionService
  ) { }

  ngOnInit(): void {
    this.cargarHabitaciones();
    this.tipoHabitacionService.listar().subscribe({
      next: (data) => {
        this.tipos = data;
      },
      error: (error) => {
        console.error('Error al obtener los tipos de habitación:', error);
      }
    });
  }

  cargarHabitaciones(): void {
    this.habitacionService.listar().subscribe({
      next: (data) => {
        this.habitaciones = data;
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    });
  }

  limpiarFormulario(): void {
    this.idEnEdicion = null;
    this.numero = '';
    this.piso = 1;
    this.precio = 0;
    this.urlImagen = '';
    this.descripcion = '';
    this.estado = 'DISPONIBLE';
    this.tipoId = 0;
  }

  editar(habitacion: Habitacion): void {
    this.idEnEdicion = habitacion.id!;
    this.numero = habitacion.numero;
    this.piso = habitacion.piso;
    this.precio = habitacion.precio;
    this.urlImagen = habitacion.urlImagen;
    this.descripcion = habitacion.descripcion;
    this.estado = habitacion.estado;
    this.tipoId = habitacion.tipo.id;
  }

  guardar(): void {
    if (this.tipoId === 0) {
      alert('Debe seleccionar un tipo de habitación.');
      return;
    }

    const habitacion: Habitacion = {
      numero: this.numero,
      piso: this.piso,
      precio: this.precio,
      urlImagen: this.urlImagen,
      descripcion: this.descripcion,
      estado: this.estado,
      tipo: { id: this.tipoId, nombre: '' }
    };

    if (this.idEnEdicion) {
      this.habitacionService.actualizar(this.idEnEdicion, habitacion).subscribe({
        next: () => {
          this.cargarHabitaciones();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo actualizar la habitación.');
        }
      });
    } else {
      this.habitacionService.crear(habitacion).subscribe({
        next: () => {
          this.cargarHabitaciones();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo crear la habitación.');
        }
      });
    }
  }

  eliminar(id: number): void {
    if (!confirm('¿Está seguro de eliminar esta habitación?')) return;
    this.habitacionService.eliminar(id).subscribe({
      next: () => {
        this.cargarHabitaciones();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error || 'No se pudo eliminar la habitación.');
      }
    });
  }
}
