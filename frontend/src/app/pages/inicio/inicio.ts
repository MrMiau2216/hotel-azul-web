import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Habitacion } from '../../models/habitacion';
import { HabitacionService } from '../../services/habitacion.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './inicio.html',
  styleUrl: './inicio.css'
})
export class Inicio implements OnInit {
  habitaciones: Habitacion[] = [];

  constructor(
    private habitacionService: HabitacionService
  ) { }

  ngOnInit(): void {
    this.habitacionService.listarPublico().subscribe({
      next: (data) => {
        this.habitaciones = data;
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    });
  }
}
