import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  username: string = '';
  password: string = '';
  mensajeError: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  iniciarSesion(): void {
    this.mensajeError = '';
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (usuario) => {
        this.authService.guardarSesion(usuario);
        this.router.navigate(['/habitaciones']);
      },
      error: (error: HttpErrorResponse) => {
        this.mensajeError = error.error || 'Usuario o contraseña incorrectos.';
      }
    });
  }
}
