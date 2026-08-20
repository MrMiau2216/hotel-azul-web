import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Habitacion } from '../../models/habitacion';
import { HabitacionService } from '../../services/habitacion.service';

@Component({
  selector: 'app-habitaciones-catalogo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './habitaciones-catalogo.html',
  styleUrl: './habitaciones-catalogo.css'
})
export class HabitacionesCatalogo implements OnInit {
  habitaciones: Habitacion[] = [];
  habitacionesFiltradas: Habitacion[] = [];
  tipos: string[] = ['Todas', 'Simple', 'Doble', 'Matrimonial', 'Suite'];
  filtroActivo: string = 'Todas';

  constructor(
    private habitacionService: HabitacionService
  ) { }

  ngOnInit(): void {
    this.habitacionService.listarPublico().subscribe({
      next: (data) => {
        this.habitaciones = data;
        this.habitacionesFiltradas = data;
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    });
  }

  filtrarPorTipo(tipo: string): void {
    this.filtroActivo = tipo;
    if (tipo === 'Todas') {
      this.habitacionesFiltradas = this.habitaciones;
    } else {
      this.habitacionesFiltradas = this.habitaciones.filter(h => h.tipo.nombre === tipo);
    }
  }
}
