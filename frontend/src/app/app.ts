import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive, Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs';
import { AuthService } from './services/auth.service';
import { Usuario } from './models/usuario';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

  seccionActual: 'publico' | 'login' | 'interno' = 'publico';
  navbarSolida: boolean = false;

  private rutasInternas = ['/habitaciones', '/huespedes', '/reservas', '/pagos'];

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.actualizarSeccion(this.router.url);
    this.router.events.pipe(
      filter((evento): evento is NavigationEnd => evento instanceof NavigationEnd)
    ).subscribe((evento) => {
      this.actualizarSeccion(evento.urlAfterRedirects);
      window.scrollTo(0, 0);
    });
  }

  @HostListener('window:scroll')
  onScroll(): void {
    this.navbarSolida = window.scrollY > 60;
  }

  private actualizarSeccion(url: string): void {
    const ruta = url.split('?')[0].split('#')[0];
    if (ruta === '/login' || ruta.startsWith('/login/')) {
      this.seccionActual = 'login';
    } else if (this.rutasInternas.some(r => ruta === r || ruta.startsWith(r + '/'))) {
      this.seccionActual = 'interno';
    } else {
      this.seccionActual = 'publico';
    }
    this.navbarSolida = window.scrollY > 60;
  }

  get usuario(): Usuario | null {
    return this.authService.obtenerSesion();
  }

  cerrarSesion(): void {
    this.authService.cerrarSesion();
    this.router.navigate(['/']);
  }
}
