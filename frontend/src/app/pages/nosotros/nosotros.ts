import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-nosotros',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nosotros.html',
  styleUrl: './nosotros.css'
})
export class Nosotros {

  facilidades = [
    { icono: '🍴', nombre: 'Restaurante', texto: 'Carta peruana de temporada, abierto para desayuno, almuerzo y cena.' },
    { icono: '📶', nombre: 'Wifi de fibra', texto: 'Conexión de alta velocidad en todas las habitaciones y áreas comunes.' },
    { icono: '🌅', nombre: 'Terraza con vista al mar', texto: 'Ideal para el pisco sour de la tarde mientras cae el sol sobre el Pacífico.' },
    { icono: '🛎️', nombre: 'Servicio a la habitación', texto: 'Disponible las 24 horas, con carta reducida durante la madrugada.' },
    { icono: '🚗', nombre: 'Estacionamiento privado', texto: 'Cochera propia y vigilada, sin costo adicional para nuestros huéspedes.' },
    { icono: '🤝', nombre: 'Sala de reuniones', texto: 'Espacio equipado para grupos pequeños de trabajo o encuentros familiares.' }
  ];

}
