import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Habitacion } from '../../models/habitacion';
import { HabitacionService } from '../../services/habitacion.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './inicio.html',
  styleUrl: './inicio.css'
})
export class Inicio implements OnInit {
  habitacionesDestacadas: Habitacion[] = [];

  lugaresTuristicos = [
    {
      nombre: 'Malecón de Miraflores',
      imagen: 'https://images.unsplash.com/photo-1533219346979-3030e2810f5b?w=1600&q=80&auto=format&fit=crop',
      descripcion: 'Acantilados y parques que corren varios kilómetros frente al mar. Al atardecer se llena de parapentes que despegan justo sobre el malecón.'
    },
    {
      nombre: 'Barranco',
      imagen: 'https://images.unsplash.com/photo-1601913463731-cfba9fd31ed3?w=1600&q=80&auto=format&fit=crop',
      descripcion: 'El barrio bohemio de Lima: el Puente de los Suspiros, murales en cada esquina y bares que abren hasta tarde. A quince minutos caminando desde el hotel.'
    },
    {
      nombre: 'Centro Histórico',
      imagen: 'https://images.unsplash.com/photo-1716116112529-3e9f2d050ed9?w=1600&q=80&auto=format&fit=crop',
      descripcion: 'La Plaza Mayor, balcones coloniales y la Catedral de Lima. Patrimonio de la Humanidad por la Unesco desde 1988, a veinte minutos en taxi.'
    },
    {
      nombre: 'Huaca Pucllana',
      imagen: 'https://images.unsplash.com/photo-1722980933523-4bc014a0145c?w=1600&q=80&auto=format&fit=crop',
      descripcion: 'Una pirámide preinca de adobe y barro, en plena ciudad, a diez cuadras del hotel. De noche la iluminan y el contraste con los edificios es único.'
    }
  ];

  constructor(
    private habitacionService: HabitacionService
  ) { }

  ngOnInit(): void {
    this.habitacionService.listarPublico().subscribe({
      next: (data) => {
        this.habitacionesDestacadas = data.slice(0, 3);
      },
      error: (error) => {
        console.error('Error al obtener las habitaciones:', error);
      }
    });
  }
}
