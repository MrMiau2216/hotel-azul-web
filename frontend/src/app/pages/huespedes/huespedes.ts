import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { Huesped } from '../../models/huesped';
import { HuespedService } from '../../services/huesped.service';

@Component({
  selector: 'app-huespedes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './huespedes.html',
  styleUrl: './huespedes.css'
})
export class Huespedes implements OnInit {
  huespedes: Huesped[] = [];

  idEnEdicion: number | null = null;
  dni: string = '';
  nombres: string = '';
  apellidos: string = '';
  telefono: string = '';
  correo: string = '';

  constructor(
    private huespedService: HuespedService
  ) { }

  ngOnInit(): void {
    this.cargarHuespedes();
  }

  cargarHuespedes(): void {
    this.huespedService.listar().subscribe({
      next: (data) => {
        this.huespedes = data;
      },
      error: (error) => {
        console.error('Error al obtener los huéspedes:', error);
      }
    });
  }

  limpiarFormulario(): void {
    this.idEnEdicion = null;
    this.dni = '';
    this.nombres = '';
    this.apellidos = '';
    this.telefono = '';
    this.correo = '';
  }

  editar(huesped: Huesped): void {
    this.idEnEdicion = huesped.id!;
    this.dni = huesped.dni;
    this.nombres = huesped.nombres;
    this.apellidos = huesped.apellidos;
    this.telefono = huesped.telefono;
    this.correo = huesped.correo;
  }

  guardar(): void {
    const huesped: Huesped = {
      dni: this.dni,
      nombres: this.nombres,
      apellidos: this.apellidos,
      telefono: this.telefono,
      correo: this.correo
    };

    if (this.idEnEdicion) {
      this.huespedService.actualizar(this.idEnEdicion, huesped).subscribe({
        next: () => {
          this.cargarHuespedes();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo actualizar el huésped.');
        }
      });
    } else {
      this.huespedService.crear(huesped).subscribe({
        next: () => {
          this.cargarHuespedes();
          this.limpiarFormulario();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error || 'No se pudo registrar el huésped.');
        }
      });
    }
  }

  eliminar(id: number): void {
    if (!confirm('¿Está seguro de eliminar este huésped?')) return;
    this.huespedService.eliminar(id).subscribe({
      next: () => {
        this.cargarHuespedes();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.error || 'No se pudo eliminar el huésped.');
      }
    });
  }
}
